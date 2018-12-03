package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName
import com.projects.bigswierku.beerstagram.model.untapped.BadgeImage

data class Badge(
        @SerializedName("badge_description")
    val badgeDescription: String,
        @SerializedName("badge_id")
    val badgeId: Int,
        @SerializedName("badge_image")
    val badgeImage: BadgeImage,
        @SerializedName("badge_name")
    val badgeName: String,
        @SerializedName("created_at")
    val createdAt: String,
        @SerializedName("user_badge_id")
    val userBadgeId: Int
)