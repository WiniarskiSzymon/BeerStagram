package com.projects.bigswierku.beerstagram.model.untapped

data class CheckInPost(
    val checkinComment: String,
    val checkinId: Int,
    val beerName : String,
    val beerStyle : String,
    val userName : String,
    val venueName: String?,
    val timeFromNow : String,
    val userAvatar : String?,
    val beerLabel : String,
    val rating: Double
)
