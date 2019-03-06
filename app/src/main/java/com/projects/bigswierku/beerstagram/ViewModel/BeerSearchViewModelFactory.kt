package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import java.lang.IllegalArgumentException
import javax.inject.Inject

class BeerSearchViewModelFactory @Inject constructor(private val untappedAPI: UntappedAPI): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(BeerImageViewModel::class.java)->
                BeerSearchViewModel(untappedAPI) as T
            else -> throw IllegalArgumentException(
                "${modelClass.simpleName} is an unknown type of view model"
            )
        }
    }

}