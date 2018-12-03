package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.BeerImageFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent
interface BeerImageComponent : AndroidInjector<BeerImageFragment>{


    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<BeerImageFragment>()
}