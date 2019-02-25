package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LogInFragmentModule(){

    @Provides
    fun providesLogInFragmentFactory(untappedAPI: UntappedAPI) = LogInViewModelFactory(untappedAPI)
}