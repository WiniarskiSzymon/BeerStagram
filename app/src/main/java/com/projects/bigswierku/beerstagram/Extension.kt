package com.projects.bigswierku.beerstagram

import com.projects.bigswierku.beerstagram.model.untapped.CheckIn
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.projects.bigswierku.beerstagram.model.untapped.ImagePost

fun CheckIn.toImagePost()=
        when (this.media.items.size){
            0-> ImagePost(
                    this.checkinComment,
                    this.checkinId,
                    this.beer.beerName,
                    this.beer.beerStyle,
                    this.brewery.breweryName,
                    this.venue.venueName,
                null,
                null,
                    this.ratingScore
            )
            else -> ImagePost(
                this.checkinComment,
                this.checkinId,
                this.beer.beerName,
                this.beer.beerStyle,
                this.brewery.breweryName,
                this.venue.venueName,
                this.media.items[0].photo.photoImgLg,
                this.media.items[0].photo.photoImgSm,
                this.ratingScore
            )
        }


fun CheckIn.toCheckInPost()=CheckInPost(
    this.checkinComment,
    this.checkinId,
    this.beer.beerName,
    this.beer.beerStyle,
    this.brewery.breweryName,
    this.venue.venueName,
    this.createdAt,
    this.user.userAvatar,
    this.beer.beerLabel,
    this.ratingScore
    )

