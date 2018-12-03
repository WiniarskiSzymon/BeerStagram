package com.projects.bigswierku.beerstagram.DI

import android.app.Application
import com.projects.bigswierku.beerstagram.BeerstagramApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class,
        ActivitiesModule::class, FragmentsModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: BeerstagramApplication)
}
