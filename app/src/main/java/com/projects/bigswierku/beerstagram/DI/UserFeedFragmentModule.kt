package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.UserFeedViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UserFeedFragmentModule(){

    @Provides
    fun providesUserFeedViewModelFactory(untappedAPI: UntappedAPI) = UserFeedViewModelFactory(untappedAPI)
}