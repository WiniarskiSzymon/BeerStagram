package com.projects.bigswierku.beerstagram.ViewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.LasLocationProvider

import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
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

    fun getCheckIns( lastId  : Int = 0) {
        val location = locationProvider.getLastKnownLocation()
        if (location != null) {
            disposable = untappedAPI.getCheckIns(lastId, location)
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
        } else {
            checkInsResponseStatus.value = ResponseStatus(Status.ERROR, "cant obtain current location")
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


