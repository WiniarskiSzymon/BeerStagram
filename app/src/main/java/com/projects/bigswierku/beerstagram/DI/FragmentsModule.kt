package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeCheckInFragment(): CheckInsFragment


    @ContributesAndroidInjector
    abstract fun contributeBeerImageFragment(): BeerImageFragment

    @ContributesAndroidInjector
    abstract fun contributeLogInFragment() : LogInFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFeedFragment() : UserFeedFragment

    @ContributesAndroidInjector
    abstract fun contributeBeerSearcFragment() : BeerSearchFragment
}