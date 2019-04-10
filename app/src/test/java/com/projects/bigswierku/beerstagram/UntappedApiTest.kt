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
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.beerInfoJSON
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.beerSearchJSON
import com.projects.bigswierku.beerstagram.JSONResponses.Companion.localCheckInsJSON
import com.projects.bigswierku.beerstagram.model.untapped.BeerInfoRequest

import com.projects.bigswierku.beerstagram.model.untapped.BeerSearchResponse
import com.projects.bigswierku.beerstagram.model.untapped.PubLocalRequest
import com.projects.bigswierku.beerstagram.model.untapped.SingletonListTypeAdapterFactory


import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import io.reactivex.observers.TestObserver
import org.junit.jupiter.api.BeforeAll
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class UntappedApiTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var mockServer : MockWebServer
    private lateinit var gson: Gson
    private lateinit var retrofit : Retrofit
    private lateinit var untappedAPI : UntappedAPI
    private lateinit var localCheckInsResponseMock : MockResponse
    private lateinit var beerinfoResponseMock : MockResponse
    private lateinit var beerSearchResponseMock  :MockResponse
    private lateinit var checkInstestSubscriber :TestObserver<PubLocalRequest>
    private lateinit var beerInfoSubscriber :TestObserver<BeerInfoRequest>
    private lateinit var beerSearchSubcriber  : TestObserver<BeerSearchResponse>
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
        gson = GsonBuilder()
            .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
            .setLenient()
            .create()
        localCheckInsResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(localCheckInsJSON)
        beerinfoResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(beerInfoJSON)
        beerSearchResponseMock = MockResponse()
            .setResponseCode(200)
            .setBody(beerSearchJSON)
    }

    @Test
    fun `Test for getting Check Ins from nearby `(){
        mockServer.enqueue(beerinfoResponseMock)
        untappedAPI.getCheckIns(lastId = 0,lastKnownLocation = location).subscribe(checkInstestSubscriber)

        checkInstestSubscriber.assertNoErrors()
        checkInstestSubscriber.assertValueCount(1)
        checkInstestSubscriber.assertValue(gson.fromJson(beerInfoJSON, PubLocalRequest::class.java))

    }

    @Test
    fun  `Test for getting specific beer info`(){
        mockServer.enqueue(localCheckInsResponseMock)
        untappedAPI.getBeerInfo("16630").subscribe(beerInfoSubscriber)

        beerInfoSubscriber.assertNoErrors()
        beerInfoSubscriber.assertValueCount(1)
        beerInfoSubscriber.assertValue(gson.fromJson(localCheckInsJSON, BeerInfoRequest::class.java))

    }

    @Test
    fun `Test for searching beer by name`(){
        mockServer.enqueue(beerSearchResponseMock)
        untappedAPI.searchBeer("beer",0).subscribe(beerSearchSubcriber)
        beerSearchSubcriber.assertNoErrors()
        beerSearchSubcriber.assertValueCount(1)
        beerSearchSubcriber.assertValue(gson.fromJson(beerInfoJSON, BeerSearchResponse::class.java))

    }

    @Test
    fun `Test for getting autentication token`(){

    }

    @Test
    fun `Test for getting user feed after logging in`(){

    }



}

