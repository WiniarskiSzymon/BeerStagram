package com.projects.bigswierku.beerstagram.model.untapped

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "friendCheckIns")
data class FriendCheckIn(
    val checkinComment: String,
    @PrimaryKey val checkinId: Int,
    val beerName : String,
    val beerStyle : String,
    val userName : String,
    val venueName: String?,
    val timeFromNow : String,
    val userAvatar : String?,
    val beerLabel : String,
    val rating: Double
)
