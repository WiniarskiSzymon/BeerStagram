package com.projects.bigswierku.beerstagram.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.projects.bigswierku.beerstagram.model.untapped.PubResponse
import com.projects.bigswierku.beerstagram.model.untapped.BeerInfoRequest
import com.projects.bigswierku.beerstagram.model.untapped.OAuthToken
import com.projects.bigswierku.beerstagram.model.untapped.PubLocalRequest
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class UntappedAPI {

    private  val clientID = "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8"
    private val clientSecret = "140D0F35382F22C9398413ADF865728F85016DA0"

    private val gson = GsonBuilder()
            .setLenient()
            .create()

    private val  retrofit = Retrofit.Builder()
            .baseUrl("https://api.untappd.com/v4/")
            .client(getLogger())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val retrofitAuth = Retrofit.Builder()
            .baseUrl("https://untappd.com/")
            .client(getLogger())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val logger = HttpLoggingInterceptor()



    fun getBeerInfo():Flowable<BeerInfoRequest>{
        val service = retrofit.create(BeerInfoService::class.java)
        return service.getBeerInfo(clientID, clientSecret)

    }

    fun getCheckIns():Flowable<PubLocalRequest>{
        val service = retrofit.create(LocalCheckInService::class.java)
        return service.getPubLocal(clientID, clientSecret,"52.2297","21.0122","km")

    }

    fun getCode(): Single<OAuthToken> {
        val service = retrofitAuth.create(UntappedAuthService::class.java)
        return service.getCode(clientID)
    }


    private fun getLogger() : OkHttpClient{
        val  logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()

    }

}