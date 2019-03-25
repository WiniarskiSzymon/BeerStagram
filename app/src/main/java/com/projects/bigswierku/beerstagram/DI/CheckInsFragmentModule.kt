package com.projects.bigswierku.beerstagram.DI

import android.app.Application
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.LasLocationProvider
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
 class CheckInsFragmentModule{
    @Provides
    fun provideLocationProvider(application : Application) = LasLocationProvider(application)

    @Provides
    fun provideCheckInViewModelFactory(untappedAPI: UntappedAPI , locationProvider: LasLocationProvider) = CheckInsViewModelFactory(untappedAPI,locationProvider)


}