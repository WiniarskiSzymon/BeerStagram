package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class OAuthToken(
        @SerializedName( "access_token")
        val   accessToken : String,
        @SerializedName("token_type")
        val  tokenType: String,
        @SerializedName("expires_in")
        val   expiresIn : Long,
        val  expiredAfterMilli : Long = 0,
        @SerializedName("refresh_token")
        val refreshToken: String
)