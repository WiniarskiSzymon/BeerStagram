package com.projects.bigswierku.beerstagram

import android.content.Context
import com.projects.bigswierku.beerstagram.model.untapped.CheckIn
import com.projects.bigswierku.beerstagram.model.untapped.CheckInPost
import com.squareup.picasso.Picasso
import java.io.File

fun CheckIn.toPost()= CheckInPost(
         this.checkinComment,
         this.checkinId,
         this.beer.beerName,
         this.beer.beerStyle,
         this.brewery.breweryName,
         this.venue.venueName,
         this.media.photo.photoImgMd ,
         this.media.photo.photoImgSm,
         this.ratingScore
    )





