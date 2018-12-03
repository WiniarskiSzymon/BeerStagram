package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.CheckInsViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class CheckInFragmentModule{

    @Provides
    fun provideCheckInViewModel(untappedAPI: UntappedAPI) = CheckInsViewModel(untappedAPI)

}