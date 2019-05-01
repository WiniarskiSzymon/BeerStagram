package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.Api.UntappedRepo
import com.projects.bigswierku.beerstagram.LasLocationProvider

import java.lang.IllegalArgumentException
import javax.inject.Inject

class CheckInsViewModelFactory @Inject constructor(private val untappedRepo: UntappedRepo , private val locationProvider : LasLocationProvider): ViewModelProvider.Factory{


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CheckInsViewModel::class.java) ->
                CheckInsViewModel(untappedRepo, locationProvider) as T
            else ->throw IllegalArgumentException(
                "${modelClass.simpleName} is an unknown type of view model"
            )
        }
    }
}