package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("venue_address")
    val adress: String,
    @SerializedName(value ="brewery_city", alternate = arrayOf("venue_city"))
    val city: String,
    @SerializedName("brewery_state", alternate = arrayOf("venue_state"))
    val state: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)

