package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class BeerImageViewModel @Inject constructor(val untappedAPI: UntappedAPI) : ViewModel(){


    private lateinit var disposable: Disposable


    var beerInfoData: MutableLiveData<List<Photo>> = MutableLiveData()
    var beerInfoRequestStatus: MutableLiveData<ResponseStatus> = MutableLiveData()

    fun getBeerInfo(beerID:String) {

        disposable = untappedAPI.getBeerInfo(beerID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { beerInfoRequestStatus.value = ResponseStatus(Status.LOADING) }
            .subscribe(
                {
                    beerInfoRequestStatus.value = ResponseStatus(Status.SUCCESS)
                    beerInfoData.value = it.response.beer.medias.items.map { it.photo }
                },
                {
                    beerInfoRequestStatus.value = ResponseStatus(Status.ERROR, it.message)
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