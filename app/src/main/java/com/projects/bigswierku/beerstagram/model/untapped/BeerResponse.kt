package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class BeerResponse(
    @SerializedName("beer")
    val beer: Beer
)