package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class PubResponse(
        @SerializedName("checkins")
        val checkins: Checkins,
        @SerializedName("dist_pref")
        val distPref: String,
        @SerializedName("radius")
        val radius: Int
)