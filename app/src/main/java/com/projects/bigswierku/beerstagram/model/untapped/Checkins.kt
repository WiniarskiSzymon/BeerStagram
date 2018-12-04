package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Checkins(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<CheckIn>
)

