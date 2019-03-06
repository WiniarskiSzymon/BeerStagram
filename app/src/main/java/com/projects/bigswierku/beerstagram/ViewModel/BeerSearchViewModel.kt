package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult
import com.projects.bigswierku.beerstagram.toBeeeSearchresult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerSearchViewModel @Inject constructor(private val untappedAPI: UntappedAPI) : ViewModel(){

    private  lateinit var disposable: Disposable

    val beerSearchData : MutableLiveData<List<BeerSearchResult>> = MutableLiveData()
    val beerSearchResponseStatus : MutableLiveData<ResponseStatus> = MutableLiveData()


    fun searchForBeer (query : String){
        disposable = untappedAPI.searchBeer(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                beerSearchResponseStatus.value = ResponseStatus(Status.LOADING)
            }
            .subscribe(
                {
                beerSearchResponseStatus.value = ResponseStatus(Status.SUCCESS)
                beerSearchData.value = it.beerSearchData.beers.items.map { it.toBeeeSearchresult() }

            },{
                    beerSearchResponseStatus.value = ResponseStatus(Status.ERROR, it.message)

                })
    }


    override fun onCleared() {
        super.onCleared()
        if (::disposable.isInitialized){
            disposable.dispose()
        }
    }

}