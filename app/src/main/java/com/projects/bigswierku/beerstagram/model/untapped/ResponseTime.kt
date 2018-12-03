package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class ResponseTime(
        @SerializedName("measure")
        val measure: String,
        @SerializedName("time")
        val time: Double
)