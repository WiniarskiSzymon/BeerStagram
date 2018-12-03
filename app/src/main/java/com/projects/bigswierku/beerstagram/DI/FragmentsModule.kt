package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.BeerImageFragment
import com.projects.bigswierku.beerstagram.View.CheckInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeCheckInFragment(): CheckInFragment


    @ContributesAndroidInjector
    abstract fun contributeBeerImageFragment(): BeerImageFragment
}