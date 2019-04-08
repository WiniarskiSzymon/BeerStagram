package com.projects.bigswierku.beerstagram.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.LasLocationProvider

import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
import com.projects.bigswierku.beerstagram.showMyDialog
import com.projects.bigswierku.beerstagram.toImagePost
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckInsViewModel @Inject constructor(private val untappedAPI: UntappedAPI,
                                            private val locationProvider : LasLocationProvider) : ViewModel(){



    private lateinit var disposable: Disposable
    var checkInsData: MutableLiveData<List<ImagePost>> = MutableLiveData()
    var checkInsResponseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()
    private var listOfImagePosts = mutableListOf<ImagePost>()
    private lateinit var lastKnownLocation  : Location
    private var locationAcquiredFlag  = false

    init{
        getLocation()
    }


    fun getCheckIns( lastId  : Int = 0) {
        if (::lastKnownLocation.isInitialized && lastKnownLocation !=null) {
            queryForCheckIns(lastId)
        }
        else{
            getLocation()
        }
    }

    private fun queryForCheckIns(lastId  : Int = 0){
        disposable = untappedAPI.getCheckIns(lastId, lastKnownLocation)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { checkInsResponseStatus.value = ResponseStatus(Status.LOADING) }
            .subscribe(
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.SUCCESS)
                    if (lastId == 0) {
                        listOfImagePosts.clear()
                    }
                    listOfImagePosts.addAll(it.response.checkins.items.map { it.toImagePost() }.filterNot { it.bigPhotoUrl.isNullOrEmpty() })
                    checkInsData.value = listOfImagePosts
                },
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.ERROR, it.message)
                })
    }
    private fun getLocation() {
        locationProvider.getLastKnownLocation().addOnSuccessListener { location: Location? ->
            if (location != null ) {
                lastKnownLocation = location
                if(!locationAcquiredFlag){
                    getCheckIns()
                    locationAcquiredFlag = true
                }
            }

            else{
                checkInsResponseStatus.value = ResponseStatus(Status.ERROR, "Cant obtain location")
            }
        }
    }

    override fun onCleared(){
        super.onCleared()
        if(::disposable.isInitialized)
        {
            disposable.dispose()
        }
    }
}


