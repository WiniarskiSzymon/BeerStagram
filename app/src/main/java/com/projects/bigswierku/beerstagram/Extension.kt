package com.projects.bigswierku.beerstagram

import android.content.Context
import com.projects.bigswierku.beerstagram.model.untapped.CheckIn
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.squareup.picasso.Picasso
import java.io.File

fun CheckIn.toPost()=
        when (this.media.items.size){
            0-> CheckInPost(
                    this.checkinComment,
                    this.checkinId,
                    this.beer.beerName,
                    this.beer.beerStyle,
                    this.brewery.breweryName,
                    this.venue.venueName,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPJDTG-CWgWfHInMFS6FlJVVHWxdefYXuOAyg3xzEKeYQp1nIU0w",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPJDTG-CWgWfHInMFS6FlJVVHWxdefYXuOAyg3xzEKeYQp1nIU0w",
                    this.ratingScore
            )
            else -> CheckInPost(
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





