package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import com.projects.bigswierku.beerstagram.toFriendCheckIn
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserFeedViewModel @Inject constructor(private val untappedAPI: UntappedAPI) : ViewModel(){


    private lateinit var disposable: Disposable

    var userFeedLiveData : MutableLiveData<List<FriendCheckIn>> = MutableLiveData()
    var responseStatus : MutableLiveData<ResponseStatus> = MutableLiveData()


    fun getUserFeed(token : String){
        disposable = untappedAPI.getUserFeed(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{responseStatus.value = ResponseStatus(Status.SUCCESS) }
            .subscribe(
                {
                    userFeedLiveData.value = it.response.checkins.items.map{it.toFriendCheckIn()}
                    responseStatus.value = ResponseStatus(Status.SUCCESS)
                },
                {responseStatus.value = ResponseStatus(Status.ERROR,it.message) }
            )
    }


    override fun onCleared() {
        super.onCleared()
        if(::disposable.isInitialized){
            disposable.dispose()
        }
    }
}