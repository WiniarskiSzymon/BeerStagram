package com.projects.bigswierku.beerstagram.DI

import android.app.Application
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
class AppModule() {


    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideApi() = UntappedAPI()

    @Provides
    fun provideFussedLocation(application: Application) = LocationServices.getFusedLocationProviderClient(application)

}