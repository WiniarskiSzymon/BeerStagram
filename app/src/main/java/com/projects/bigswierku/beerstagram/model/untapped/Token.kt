package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val token:Token
)