package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class BeerSearchData(
    @SerializedName("beers")
    val beers: Beers,
    @SerializedName("breweries")
    @Transient
    val breweries: Any,
    @SerializedName("brewery_id")
    val breweryId: Int,
    @SerializedName("found")
    val found: Int,
    @SerializedName("homebrew")
    @Transient
    val homebrew: Any,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("parsed_term")
    val parsedTerm: String,
    @SerializedName("search_type")
    val searchType: String,
    @SerializedName("search_version")
    val searchVersion: Int,
    @SerializedName("term")
    val term: String,
    @SerializedName("time_taken")
    val timeTaken: Double,
    @SerializedName("type_id")
    val typeId: Int
)