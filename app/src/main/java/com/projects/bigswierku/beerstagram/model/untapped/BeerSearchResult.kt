package com.projects.bigswierku.beerstagram.model.untapped

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BeerSearchResult(
    val beerName: String,
    val breweryName : String,
    val description : String,
    val originCountry  : String,
    val beerId : Int,
    val beerLabel : String,
    val checkInsCount : Int
)