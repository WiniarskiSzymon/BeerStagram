package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class InitTime(
        @SerializedName("measure")
        val measure: String,
        @SerializedName("time")
        val time: Int
)