package com.projects.bigswierku.beerstagram

import android.location.Location
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel

import com.projects.bigswierku.beerstagram.model.untapped.*


import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import retrofit2.adapter.rxjava2.Result.response






//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner::class)
class ViewModelsTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

//    @Rule
//    var mockitoRule = MockitoJUnit.rule()



    private var untappedAPI =mock<UntappedAPI>()
    private var locationProvider = mock<LasLocationProvider>()

    private lateinit var  checkInsViewModelMock : CheckInsViewModel

    @InjectMocks
    private lateinit var beerImageViewModelMock : BeerImageViewModel

    private lateinit var gson: Gson
    private lateinit var checkInRespons : PubLocalRequest
    private lateinit var beerInfoResponse: BeerInfoRequest
    private val location = mock<Location>()
    private val checkInsObserver = mock<Observer<List<ImagePost>>>()
    private val beerInfoObserver = mock<Observer<List<Photo>>>()
    private val taskMock = mock<Task<Location>>()
    private val localCheckInsJSON = JSONResponses.localCheckInsJSON
    private val beerInfoJSON =JSONResponses.beerInfoJSON

    @get:Rule
    val schedulers = RxImmediateSchedulerRule()

        @Before
        fun setup(){
            MockitoAnnotations.initMocks(this)
            this.gson = GsonBuilder()
                .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
                .setLenient()
                .create()

            checkInRespons = gson.fromJson(localCheckInsJSON, PubLocalRequest::class.java)
            beerInfoResponse = gson.fromJson(beerInfoJSON, BeerInfoRequest::class.java)
            whenever(taskMock.addOnSuccessListener {  }).thenReturn(location)
            whenever(locationProvider.getLastKnownLocation()).thenReturn(taskMock)
            checkInsViewModelMock  = CheckInsViewModel(untappedAPI,locationProvider)
        }



    @Test
    fun `Checking passing check in info from api to activity`(){
        whenever(untappedAPI.getCheckIns(0,location)).thenReturn(Single.just(checkInRespons))
        checkInsViewModelMock.checkInsData.observeForever(checkInsObserver)
        checkInsViewModelMock.getCheckIns()
        verify(checkInsObserver).onChanged(checkInRespons.response.checkins.items.map { it.toImagePost() }.filterNot { it.bigPhotoUrl.isNullOrEmpty() })

    }

    @Test
    fun `Checking passing beer info from api to activity`(){
        whenever(untappedAPI.getBeerInfo("1666")).thenReturn(Single.just(beerInfoResponse))
        beerImageViewModelMock.getBeerInfo("1666")
        beerImageViewModelMock.beerInfoData.observeForever(beerInfoObserver)
        verify(beerInfoObserver).onChanged(beerInfoResponse.response.beer.medias.items.map { it.photo })
    }

}
