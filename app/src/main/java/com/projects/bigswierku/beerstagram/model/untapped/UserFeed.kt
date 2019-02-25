package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class UserFeed(
    @SerializedName("pagination")
    @Transient
    val Pagination : Any,
    @SerializedName("checkins")
    val checkins: Checkins
)