package com.projects.bigswierku.beerstagram

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
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
                    this.venue[0].venueName,
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
                this.venue[0].venueName,
                this.media.items[0].photo.photoImgLg,
                this.media.items[0].photo.photoImgSm,
                this.ratingScore
            )
        }


fun CheckIn.toCheckInPost()=
    when (this.venue.size){
        0-> CheckInPost(
            this.checkinComment,
            this.checkinId,
            this.beer.beerName,
            this.beer.beerStyle,
            this.user.userName,
            null,
            this.createdAt,
            this.user.userAvatar,
            this.beer.beerLabel,
            this.ratingScore
    )
        else -> CheckInPost(
            this.checkinComment,
            this.checkinId,
            this.beer.beerName,
            this.beer.beerStyle,
            this.user.userName,
            this.venue[0].venueName,
            this.createdAt,
            this.user.userAvatar,
            this.beer.beerLabel,
            this.ratingScore
        )
    }

 fun Context.showMyDialog(errorMessage:String?) {
    val builder = AlertDialog.Builder(this)
     with(builder) {
         setTitle(R.string.error_alert_title)
         setMessage(R.string.error_alert_message)
         setPositiveButton(R.string.error_alert_ok) { dialog : DialogInterface, _ ->
             dialog.dismiss()
         }
         setNegativeButton(R.string.error_alert_more_info) { dialog: DialogInterface, _ ->
             dialog.dismiss()
             showMyDialog(errorMessage)
         }
     }
    val dialog = builder.create()
    dialog.show()
}
