package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.projects.bigswierku.beerstagram.toPost
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckInsViewModel @Inject constructor(private val untappedAPI: UntappedAPI) : ViewModel(){


    private lateinit var disposable: Disposable


    var checkInsData: MutableLiveData<List<CheckInPost>> = MutableLiveData()
    var checkInsResponseStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    fun getCheckIns() {

        disposable = untappedAPI.getCheckIns()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { checkInsResponseStatus.value = ResponseStatus(Status.LOADING) }
            .subscribe(
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.SUCCESS)
                    checkInsData.value = it.response.checkins.items.map { it.toPost() }
                },
                {
                    checkInsResponseStatus.value = ResponseStatus(Status.ERROR, it.message)
                })
    }


    override fun onCleared(){
        super.onCleared()
        if(::disposable.isInitialized)
        {
            disposable.dispose()
        }
    }
}


