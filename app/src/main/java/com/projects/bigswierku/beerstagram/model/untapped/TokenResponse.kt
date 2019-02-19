package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("meta")
    @Transient
    val meta: Meta,
    @SerializedName("response")
    val token : Token
)


