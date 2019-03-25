package com.projects.bigswierku.beerstagram.DI

import android.app.Application
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.BeerSearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class BeerSearchFragmentModule{

    @Provides
    fun provideBeerSearchViewModelFactory(untappedAPI: UntappedAPI)= BeerSearchViewModelFactory(untappedAPI)
}