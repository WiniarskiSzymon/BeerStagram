package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class BeerImageModule{

    @Provides
    fun provideBeerImageViewModel(untappedAPI: UntappedAPI) = BeerImageViewModel(untappedAPI)
}