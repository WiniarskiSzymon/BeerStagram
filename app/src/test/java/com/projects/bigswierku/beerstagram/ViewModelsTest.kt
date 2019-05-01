package com.projects.bigswierku.beerstagram

import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.Api.UntappedRepo
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerInfoResponse
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerSearchResponse
import com.projects.bigswierku.beerstagram.DataHelper.Companion.checkInRespons
import com.projects.bigswierku.beerstagram.DataHelper.Companion.tokenResponse
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModel

import com.projects.bigswierku.beerstagram.model.untapped.*
import io.reactivex.Observable


import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner






//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner::class)
class ViewModelsTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

//    @Rule
//    var mockitoRule = MockitoJUnit.rule()



    private var untappedRepo =mock<UntappedRepo>()
    private var untappedAPI =mock<UntappedAPI>()

    private var locationProvider = mock<LasLocationProvider>()

    private lateinit var  checkInsViewModelMock : CheckInsViewModel

    @InjectMocks
    private lateinit var beerImageViewModelMock : BeerImageViewModel

    @InjectMocks
    private lateinit var beerSearchViewModel : BeerSearchViewModel

    @InjectMocks
    private lateinit var logInViewModel : LogInViewModel

    private val location = mock<Location>()
    private val checkInsObserver = mock<Observer<List<LocalCheckIn>>>()
    private val beerInfoObserver = mock<Observer<List<Photo>>>()
    private val beerSearchObserver = mock<Observer<List<BeerSearchResult>>>()
    private val tokenObserver = mock<Observer<Token>>()
    private val taskMock = mock<Task<Location>>()
    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

        @Before
        fun setup(){
            MockitoAnnotations.initMocks(this)

           // whenever(taskMock.addOnSuccessListener {  }).thenReturn(location)
            whenever(locationProvider.getLastKnownLocation()).thenReturn(taskMock)
            checkInsViewModelMock  = CheckInsViewModel(untappedRepo ,locationProvider)
        }



    @Test
    fun `Checking passing check in info from api to activity`(){
        val lis2 = mutableListOf<LocalCheckIn>()
        val list = checkInRespons.response.checkins.items
        val list3 = list.map { it.toLocalCheckIn() }
        whenever(untappedRepo.getLocalCheckIns(0,location)).thenReturn(Observable.just(list3))
        checkInsViewModelMock.checkInsData.observeForever(checkInsObserver)
        checkInsViewModelMock.getCheckIns()
        verify(checkInsObserver).onChanged(checkInRespons.response.checkins.items.map { it.toLocalCheckIn() }.filterNot { it.bigPhotoUrl.isNullOrEmpty() })

    }

    @Test
    fun `Checking passing beer info from api to activity`(){
        whenever(untappedAPI.getBeerInfo("1666")).thenReturn(Single.just(beerInfoResponse))
        beerImageViewModelMock.getBeerInfo("1666")
        beerImageViewModelMock.beerInfoData.observeForever(beerInfoObserver)
        verify(beerInfoObserver).onChanged(beerInfoResponse.response.beer.medias.items.map { it.photo })
    }

    @Test
    fun `Checking passing beer search results`(){
        whenever(untappedAPI.searchBeer("beer",1)).thenReturn(Single.just(beerSearchResponse))
        beerSearchViewModel.searchForBeer("beer",1)
        beerSearchViewModel.beerSearchData.observeForever(beerSearchObserver)
        verify(beerSearchObserver).onChanged((beerSearchResponse.beerSearchData.beers.items.map { it.toBeeeSearchresult() }))
    }

    @Test
    fun `Checking passing user feed`(){
        //whenever(untappedAPI.getUserFeed("token")).thenReturn(Single.just())

    }


    @Test
    fun `Checking getting token`(){
        whenever(untappedAPI.getToken("code")).thenReturn(Single.just(tokenResponse))
        logInViewModel.getAuthorizationToken("code")
        logInViewModel.tokenLiveData.observeForever(tokenObserver)
        verify(tokenObserver).onChanged(tokenResponse.token)
    }
}
