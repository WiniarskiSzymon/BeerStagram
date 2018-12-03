package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Beer(
        @SerializedName("auth_rating")
    val authRating: Int,
        @SerializedName("beer_abv")
    val beerAbv: Double,
        @SerializedName("beer_description")
    val beerDescription: String,
        @SerializedName("beer_ibu")
    val beerIbu: Int,
        @SerializedName("beer_label")
    val beerLabel: String,
        @SerializedName("beer_name")
    val beerName: String,
        @SerializedName("beer_slug")
    val beerSlug: String,
        @SerializedName("beer_style")
    val beerStyle: String,
        @SerializedName("bid")
    val bid: Int,
        @SerializedName("brewery")
    val brewery: Brewery,
        @SerializedName("created_at")
    val createdAt: String,
        @SerializedName("friends")
    val friends: Friends,
        @SerializedName("is_homebrew")
    val isHomebrew: Int,
        @SerializedName("is_in_production")
    val isInProduction: Int,
        @SerializedName("media")
    val medias: Medias,
        @SerializedName("rating_count")
    val ratingCount: Int,
        @SerializedName("rating_score")
    val ratingScore: Double,
        @SerializedName("stats")
    val stats: Stats


)