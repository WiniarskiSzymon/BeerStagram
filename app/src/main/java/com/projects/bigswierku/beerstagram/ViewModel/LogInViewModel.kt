package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.Token
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Dispatcher
import javax.inject.Inject

class LogInViewModel @Inject constructor(private val untappedAPI: UntappedAPI) : ViewModel(){


    private lateinit var disposable : Disposable
    var tokenLiveData : MutableLiveData<Token> = MutableLiveData()
    var responseLiveData : MutableLiveData<ResponseStatus> = MutableLiveData()


    fun getAuthorizationToken(code : String){
        disposable = untappedAPI.getToken(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseLiveData.value = ResponseStatus(Status.LOADING) }
            .subscribe (
                {
                    tokenLiveData.value = it.token
                    responseLiveData.value = ResponseStatus(Status.SUCCESS)
                },
                {
                    responseLiveData.value = ResponseStatus(Status.ERROR, it.message)
                }
            )

    }

    override fun onCleared() {
        super.onCleared()
        if(::disposable.isInitialized){
            disposable.dispose()
        }
    }
}

