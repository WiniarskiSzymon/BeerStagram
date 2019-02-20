package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CheckInsViewModelFactory @Inject constructor(private val untappedApi : UntappedAPI): ViewModelProvider.Factory{


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CheckInsViewModel::class.java) ->{
                CheckInsViewModel(untappedApi) as T
            }
            else ->throw IllegalArgumentException(
                "${modelClass.simpleName} is an unknown type of view model"
            )
        }
    }
}