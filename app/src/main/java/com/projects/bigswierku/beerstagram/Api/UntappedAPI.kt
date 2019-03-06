package com.projects.bigswierku.beerstagram.Api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.Gson

import com.projects.bigswierku.beerstagram.model.untapped.*




class UntappedAPI {

    private  val clientID = "7BA7E574D1C0CEFCEB7FDAB198D5A68F402FC9A8"
    private val clientSecret = "140D0F35382F22C9398413ADF865728F85016DA0"


    private val gson = GsonBuilder()
        .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
            .setLenient()
            .create()

    private val  retrofit = Retrofit.Builder()
            .baseUrl("https://api.untappd.com/v4/")
            .client(getLogger())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val logger = HttpLoggingInterceptor()
    private val service =  retrofit.create(UntappedService::class.java)


    fun getBeerInfo(): Single<BeerInfoRequest> =service.getBeerInfo(clientID, clientSecret)

    fun getCheckIns(lastId: Int):Single<PubLocalRequest> = service.getPubLocal(clientID, clientSecret,"52.2297","21.0122","km", lastId.toString())

    fun getToken(code : String):Single<TokenResponse> = service.getAuthorizationToken("https://untappd.com/oauth/authorize/",
        clientID, clientSecret, "code", "open.my.app",code)

    fun getUserFeed(token : String):Single<UserFeedResponse> = service.getUserFeed(token)

    private fun getLogger() : OkHttpClient{
        val  logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()

    }

}