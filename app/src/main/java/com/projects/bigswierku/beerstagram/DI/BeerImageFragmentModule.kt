package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModel
import com.projects.bigswierku.beerstagram.ViewModel.BeerImageViewModelFactory
import dagger.Module
import dagger.Provides

@Module
 class BeerImageFragmentModule{

    @Provides
    fun provideBeerImageViewModeFactory(untappedAPI: UntappedAPI) = BeerImageViewModelFactory(untappedAPI)
}