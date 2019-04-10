package com.projects.bigswierku.beerstagram

import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Rule
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.beerInfoJSON
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.beerSearchJSON
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.localCheckInsJSON
import com.projects.bigswierku.beerstagram.model.untapped.*
import io.reactivex.Single


import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@DisplayName("Tests of ViewModels")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ViewModelsTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private val untappedAPI = mock<UntappedAPI>()

    @InjectMocks
    private lateinit var checkInsViewModelMock : CheckInsViewModel

    @InjectMocks
    private lateinit var beerImageViewModelMock : BeerImageViewModel

    private lateinit var gson: Gson
    private lateinit var checkInRespons : PubLocalRequest
    private lateinit var beerInfoResponse: BeerInfoRequest
    private  var location = mock<Location>()
    private var checkInsObserver = mock<Observer<List<ImagePost>>>()
    private var beerInfoObserver = mock<Observer<List<Photo>>>()



    @BeforeAll
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        gson = GsonBuilder()
            .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
            .setLenient()
            .create()
        checkInRespons = gson.fromJson(localCheckInsJSON, PubLocalRequest::class.java)
        beerInfoResponse = gson.fromJson(beerInfoJSON, BeerInfoRequest::class.java)

    }

    @Test
    @DisplayName("Checking passing check in info from api to activity")
    fun testGetOneCheckInPost(){
        given(untappedAPI.getCheckIns(0,location)).willReturn(Single.just(checkInRespons))
        checkInsViewModelMock.checkInsData.observeForever(checkInsObserver)
        checkInsViewModelMock.getCheckIns()
        verify(checkInsObserver).onChanged(checkInRespons.response.checkins.items.map { it.toImagePost() }.filterNot { it.bigPhotoUrl.isNullOrEmpty() })

    }

    @Test
    @DisplayName("Checking passing beer info from api to activity")
    fun testGetOneBeerPhoto(){
        given(untappedAPI.getBeerInfo("1554")).willReturn(Single.just(beerInfoResponse))
        beerImageViewModelMock.beerInfoData.observeForever(beerInfoObserver)
        beerImageViewModelMock.getBeerInfo("1666")
        verify(beerInfoObserver).onChanged(beerInfoResponse.response.beer.medias.items.map { it.photo })
    }

}