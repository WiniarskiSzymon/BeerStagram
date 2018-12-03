package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Foursquare(
        @SerializedName("foursquare_id")
        val foursquareId: String,
        @SerializedName("foursquare_url")
        val foursquareUrl: String
)