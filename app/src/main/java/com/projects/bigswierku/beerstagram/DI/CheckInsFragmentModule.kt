package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
 class CheckInsFragmentModule{

    @Provides
    fun provideCheckInViewModelFactory(untappedAPI: UntappedAPI) = CheckInsViewModelFactory(untappedAPI)

}