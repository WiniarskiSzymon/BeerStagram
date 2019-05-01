package com.projects.bigswierku.beerstagram.model.untapped

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localCheckIns")
data class LocalCheckIn(
        val checkinComment: String,
        @PrimaryKey val checkinId: Int,
        val beerName : String,
        val beerStyle : String,
        val breweryName : String,
        val cityName: String,
        val bigPhotoUrl : String?,
        val smallPhotoUrl : String?,
        val rating: Double,
        val userName : String)