package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("facebook")
    val facebook: String,
    @SerializedName("twitter")
    val twitter: String,
    @SerializedName("url", alternate = arrayOf("venue_url"))
    val url: String
)