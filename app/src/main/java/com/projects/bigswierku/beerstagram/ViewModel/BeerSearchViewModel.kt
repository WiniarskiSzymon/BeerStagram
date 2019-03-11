package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResult
import com.projects.bigswierku.beerstagram.model.untapped.ImagePost
import com.projects.bigswierku.beerstagram.toBeeeSearchresult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BeerSearchViewModel @Inject constructor(private val untappedAPI: UntappedAPI) : ViewModel(){

    private  lateinit var disposable: Disposable
    val beerSearchData : MutableLiveData<List<BeerSearchResult>> = MutableLiveData()
    val beerSearchResponseStatus : MutableLiveData<ResponseStatus> = MutableLiveData()
    private var listOfBeerSearchResult = mutableListOf<BeerSearchResult>()


    fun searchForBeer (query : String, offset : Int = 0){
        disposable = untappedAPI.searchBeer(query, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                beerSearchResponseStatus.value = ResponseStatus(Status.LOADING)
            }
            .subscribe(
                {
                    beerSearchResponseStatus.value = ResponseStatus(Status.SUCCESS)
                    if(offset ==0) {
                        listOfBeerSearchResult.clear()
                    }
                    listOfBeerSearchResult.addAll( it.beerSearchData.beers.items.map { it.toBeeeSearchresult() })
                    beerSearchData.value = listOfBeerSearchResult
                },
                {
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