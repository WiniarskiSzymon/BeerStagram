package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.BeerImageFragment
import com.projects.bigswierku.beerstagram.View.CheckInsFragment
import com.projects.bigswierku.beerstagram.View.LogInFragment
import com.projects.bigswierku.beerstagram.View.UserFeedFragment
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
}