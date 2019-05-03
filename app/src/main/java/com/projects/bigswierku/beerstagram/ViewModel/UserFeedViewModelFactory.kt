package com.projects.bigswierku.beerstagram.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.Api.UntappedRepo
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserFeedViewModelFactory @Inject constructor(private val untappedRepo: UntappedRepo) : ViewModelProvider.Factory{


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{modelClass.isAssignableFrom(UserFeedViewModel::class.java)->{
            UserFeedViewModel(untappedRepo) as T
        }
            else -> throw IllegalArgumentException(
                "${modelClass.simpleName} is an unknown type of ViewModel"
            )

        }
    }
}