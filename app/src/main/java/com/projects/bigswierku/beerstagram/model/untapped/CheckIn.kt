package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName


data class CheckIn(
        @SerializedName("badges")
    val badges: Badges,
        @SerializedName("beer")
    val beer: Beer,
        @SerializedName("brewery")
    val brewery: Brewery,
        @SerializedName("checkin_comment")
    val checkinComment: String,
        @SerializedName("checkin_id")
    val checkinId: Int,
        @SerializedName("comments")
    val comments: Comments,
        @SerializedName("created_at")
    val createdAt: String,
        @SerializedName("distance")
    val distance: Double,
        @SerializedName("media")
    val media: Media,
        @SerializedName("rating_score")
    val ratingScore: Int,
        @SerializedName("source")
    val source: Source,
        @SerializedName("toasts")
    val toasts: Toasts,
        @SerializedName("user")
    val user: User,
        @SerializedName("venue")
    val venue: Venue
)