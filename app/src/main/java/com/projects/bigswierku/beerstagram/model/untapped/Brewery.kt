package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Brewery(
    @SerializedName("brewery_id")
    val breweryId: Int,
    @SerializedName("brewery_label")
    val breweryLabel: String,
    @SerializedName("brewery_name")
    val breweryName: String,
    @SerializedName("contact")
    val contact: Contact,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("location")
    val location: Location
)