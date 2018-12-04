package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Media(
        @SerializedName("brewery")
        val brewery: Brewery,
        @SerializedName("checkin_id")
        val checkinId: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("photo")
        val _photo: Photo? = Photo(),
        @SerializedName("photo_id")
        val photoId: Int,
        @SerializedName("user")
        val user: User,
        @SerializedName("venue")
        @Transient
        val venue: List<Venue>
){
        val photo
                get() = _photo ?: Photo()
}