package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Friends(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<Any>
)