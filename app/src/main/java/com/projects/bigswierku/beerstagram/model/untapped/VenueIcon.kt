package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class VenueIcon(
        @SerializedName("lg")
        val lg: String,
        @SerializedName("md")
        val md: String,
        @SerializedName("sm")
        val sm: String
)