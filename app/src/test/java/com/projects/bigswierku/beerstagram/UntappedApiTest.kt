package com.projects.bigswierku.beerstagram

import com.projects.bigswierku.beerstagram.Api.UntappedAPI

import org.junit.Before
import org.junit.Rule

import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import android.location.Location

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import com.nhaarman.mockitokotlin2.mock
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerInfoJSON
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerInfoResponse
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerSearchJSON
import com.projects.bigswierku.beerstagram.DataHelper.Companion.beerSearchResponse
import com.projects.bigswierku.beerstagram.DataHelper.Companion.checkInRespons
import com.projects.bigswierku.beerstagram.DataHelper.Companion.localCheckInsJSON
import com.projects.bigswierku.beerstagram.DataHelper.Companion.tokenJSON
import com.projects.bigswierku.beerstagram.DataHelper.Companion.tokenResponse
import com.projects.bigswierku.beerstagram.model.untapped.*


import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import io.reactivex.observers.TestObserver
import org.junit.BeforeClass
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class UntappedApiTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var mockServer : MockWebServer
    private lateinit var retrofit : Retrofit
    private lateinit var untappedAPI : UntappedAPI
    private lateinit var localCheckInsResponseMock : MockResponse
    private lateinit var beerinfoResponseMock : MockResponse
    private lateinit var beerSearchResponseMock  :MockResponse
    private lateinit var tokenResponseMock : MockResponse
    private lateinit var checkInstestSubscriber :TestObserver<PubLocalRequest>
    private lateinit var beerInfoSubscriber :TestObserver<BeerInfoRequest>
    private lateinit var beerSearchSubcriber  : TestObserver<BeerSearchResponse>
    private lateinit var tokenSubscriber : TestObserver<TokenResponse>
    private  var location = mock<Location>()



    @Before
    fun setUp() {
        mockServer = MockWebServer()
        MockitoAnnotations.initMocks(this)
        retrofit = Retrofit.Builder()
            .baseUrl(mockServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        untappedAPI=UntappedAPI(retrofit)
        checkInstestSubscriber = TestObserver()
        beerInfoSubscriber = TestObserver()
        beerSearchSubcriber = TestObserver()
        tokenSubscriber = TestObserver()
        localCheckInsResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(localCheckInsJSON)
        beerinfoResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(beerInfoJSON)
        beerSearchResponseMock = MockResponse()
            .setResponseCode(200)
            .setBody(beerSearchJSON)
        tokenResponseMock = MockResponse()
            .setResponseCode(200)
            .setBody(tokenJSON)
    }

    @Test
    fun `Test for getting Check Ins from nearby `(){
        mockServer.enqueue(localCheckInsResponseMock)
        untappedAPI.getCheckIns(lastId = 0,lastKnownLocation = location).subscribe(checkInstestSubscriber)

        checkInstestSubscriber.assertNoErrors()
        checkInstestSubscriber.assertValueCount(1)
        checkInstestSubscriber.assertValue(checkInRespons)

    }

    @Test
    fun  `Test for getting specific beer info`(){
        mockServer.enqueue(beerinfoResponseMock)
        untappedAPI.getBeerInfo("16630").subscribe(beerInfoSubscriber)

        beerInfoSubscriber.assertNoErrors()
        beerInfoSubscriber.assertValueCount(1)
        beerInfoSubscriber.assertValue(beerInfoResponse)

    }

    @Test
    fun `Test for searching beer by name`(){
        mockServer.enqueue(beerSearchResponseMock)
        untappedAPI.searchBeer("beer",0).subscribe(beerSearchSubcriber)
        beerSearchSubcriber.assertNoErrors()
        beerSearchSubcriber.assertValueCount(1)
        beerSearchSubcriber.assertValue(beerSearchResponse)

    }

    @Test
    fun `Test for getting autentication token`(){
        mockServer.enqueue(tokenResponseMock)
        untappedAPI.getToken("16630").subscribe(tokenSubscriber)
        tokenSubscriber.assertNoErrors()
        tokenSubscriber.assertValueCount(1)
        tokenSubscriber.assertValue(tokenResponse)

    }

    @Test
    fun `Test for getting user feed after logging in`(){

    }



}

