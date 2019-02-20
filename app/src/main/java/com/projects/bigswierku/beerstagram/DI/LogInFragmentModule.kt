package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.LogInViewModelFavtory
import dagger.Module
import dagger.Provides

@Module
class LogInFragmentModule(){

    @Provides
    fun providesLogInFragmentFactory(untappedAPI: UntappedAPI) = LogInViewModelFavtory(untappedAPI)
}