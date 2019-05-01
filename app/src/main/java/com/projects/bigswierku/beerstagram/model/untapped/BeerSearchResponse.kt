package com.projects.bigswierku.beerstagram.model.untapped


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class BeerSearchResponse(
    @SerializedName("meta")
    @Transient
    val meta: Meta,
    @SerializedName("notifications")
    @Transient
    val notifications: List<Any>,
    @SerializedName("response")
    val beerSearchData: BeerSearchData
)