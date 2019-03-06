package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Beers(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<BeerSearchResultItem>
)