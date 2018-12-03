package com.projects.bigswierku.beerstagram

import android.app.Activity
import android.app.Application
import com.projects.bigswierku.beerstagram.DI.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BeerstagramApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var  activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }
}