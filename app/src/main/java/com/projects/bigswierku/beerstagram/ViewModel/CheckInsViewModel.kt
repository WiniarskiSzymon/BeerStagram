package com.projects.bigswierku.beerstagram.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedRepo
import com.projects.bigswierku.beerstagram.LasLocationProvider

import com.projects.bigswierku.beerstagram.model.untapped.LocalCheckIn
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckInsViewModel @Inject constructor(private val untappedRepo: UntappedRepo,
                                            private val locationProvider : LasLocationProvider) : ViewModel(){



    private lateinit var disposable: Disposable
    var checkInsData: MutableLiveData<List<LocalCheckIn>> = MutableLiveData()
    var checkInsResponseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()
    private var listOfImagePosts = mutableListOf<LocalCheckIn>()
    private lateinit var lastKnownLocation  : Location
    private var locationAcquiredFlag  = false

    init{
        getLocation()
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


    fun getCheckIns( lastId  : Int = 0) {
        if (::lastKnownLocation.isInitialized) {
            queryForCheckIns(lastId)
        }
        else{
            getLocation()
        }
    }

    private fun queryForCheckIns(lastId  : Int = 0){
        disposable = untappedRepo.getLocalCheckIns(lastId, lastKnownLocation)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { checkInsResponseStatus.value = ResponseStatus(Status.LOADING) }
            .subscribe(
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.SUCCESS)
                    checkInsData.value = it.filterNot { it.bigPhotoUrl.isNullOrEmpty() }
                },
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.ERROR, it.message)
                })
    }


    fun getMoreCheckIns(lastId: Int){
        untappedRepo.getMoreCheckIns(lastId, lastKnownLocation)
    }



    override fun onCleared(){
        super.onCleared()
        if(::disposable.isInitialized)
        {
            disposable.dispose()
        }
    }
}


