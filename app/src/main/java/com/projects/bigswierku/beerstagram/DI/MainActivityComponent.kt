package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity>{



    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainActivity>()
}

