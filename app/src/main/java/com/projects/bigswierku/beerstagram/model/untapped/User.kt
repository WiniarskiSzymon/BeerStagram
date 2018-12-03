package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName



data class User(
        @SerializedName("bio")
        val bio: String,
        @SerializedName("contact")
        val contact: Contact,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("is_private")
        val isPrivate: Int,
        @SerializedName("is_supporter")
        val isSupporter: Int,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("relationship")
        val relationship: String,
        @SerializedName("uid")
        val uid: Int,
        @SerializedName("url")
        val url: String,
        @SerializedName("user_avatar")
        val userAvatar: String,
        @SerializedName("user_name")
        val userName: String
)