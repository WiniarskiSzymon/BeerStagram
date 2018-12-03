package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("code")
        val code: Int,
        @SerializedName("init_time")
        val initTime: InitTime,
        @SerializedName("response_time")
        val responseTime: ResponseTime
)