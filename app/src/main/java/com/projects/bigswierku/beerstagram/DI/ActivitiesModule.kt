package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun contributeMainActivity(): MainActivity
}
