package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName
data class BeerSearchResultItem(
    @SerializedName("beer")
    val beer: Beer,
    @SerializedName("brewery")
    val brewery: Brewery,
    @SerializedName("checkin_count")
    val checkinCount: Int,
    @SerializedName("have_had")
    val haveHad: Boolean,
    @SerializedName("your_count")
    val yourCount: Int
)