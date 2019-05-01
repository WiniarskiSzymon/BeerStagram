package com.projects.bigswierku.beerstagram

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.projects.bigswierku.beerstagram.model.untapped.*


fun CheckIn.toLocalCheckIn()=
        when (this.media.items.size){
            0-> LocalCheckIn(
                    this.checkinComment,
                    this.checkinId,
                    this.beer.beerName,
                    this.beer.beerStyle,
                    this.brewery.breweryName,
                    this.venue[0].venueName,
                    null,
                    null,
                    this.ratingScore,
                    this.user.userName
            )
            else -> LocalCheckIn(
                this.checkinComment,
                this.checkinId,
                this.beer.beerName,
                this.beer.beerStyle,
                this.brewery.breweryName,
                this.venue[0].venueName,
                this.media.items[0].photo.photoImgLg,
                this.media.items[0].photo.photoImgSm,
                this.ratingScore,
                this.user.userName
            )
        }


fun CheckIn.toFriendCheckIn()=
    when (this.venue.size){
        0-> FriendCheckIn(
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
        else -> FriendCheckIn(
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

fun BeerSearchResultItem.toBeeeSearchresult() = BeerSearchResult(
    this.beer.beerName,
    this.brewery.breweryName,
    this.beer.beerDescription,
    this.brewery.countryName,
    this.beer.bid,
    this.beer.beerLabel,
    this.checkinCount
)

 fun Context.showMyDialog(errorMessage:String?, moreFlag : Boolean = false) {
    val builder = AlertDialog.Builder(this)
     with(builder) {
         setTitle(R.string.error_alert_title)
         if(moreFlag){
             setMessage(errorMessage)
         }else {
             setMessage(R.string.error_alert_message)
         }
         setPositiveButton(R.string.error_alert_ok) { dialog : DialogInterface, _ ->
             dialog.dismiss()
         }
         setNegativeButton(R.string.error_alert_more_info) { dialog: DialogInterface, _ ->
             dialog.dismiss()
             showMyDialog(errorMessage, true)
         }
     }
    val dialog = builder.create()
    dialog.show()
}


