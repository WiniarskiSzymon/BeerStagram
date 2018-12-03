package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("app_name")
    val appName: String,
    @SerializedName("app_website")
    val appWebsite: String
)