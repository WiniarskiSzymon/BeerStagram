package com.projects.bigswierku.beerstagram.DI

import com.projects.bigswierku.beerstagram.View.CheckInFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent
interface CheckInFragmentComponent : AndroidInjector<CheckInFragment> {


    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<CheckInFragment>()
}
