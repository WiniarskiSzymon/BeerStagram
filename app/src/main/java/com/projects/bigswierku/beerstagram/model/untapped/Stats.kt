package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("monthly_count")
    val monthlyCount: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("total_user_count")
    val totalUserCount: Int,
    @SerializedName("user_count")
    val userCount: Int
)