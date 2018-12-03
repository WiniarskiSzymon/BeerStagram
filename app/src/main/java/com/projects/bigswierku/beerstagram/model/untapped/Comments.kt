package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<Any>,
    @SerializedName("total_count")
    val totalCount: Int
)