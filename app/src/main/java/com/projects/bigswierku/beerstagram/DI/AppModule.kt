package com.projects.bigswierku.beerstagram.DI

import android.app.Application
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.SingletonListTypeAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule() {


    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson) = Retrofit.Builder()
        .baseUrl("https://api.untappd.com/v4/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideGson() = GsonBuilder()
        .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
        .setLenient()
        .create()


    @Provides
    fun provideFussedLocation(application: Application) = LocationServices.getFusedLocationProviderClient(application)

}