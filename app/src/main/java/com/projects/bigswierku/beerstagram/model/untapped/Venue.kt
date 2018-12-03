package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Venue(
        @SerializedName("categories")
        val categories: Categories,
        @SerializedName("foursquare")
        val foursquare: Foursquare,
        @SerializedName("parent_category_id")
        val parentCategoryId: String,
        @SerializedName("primary_category")
        val primaryCategory: String,
        @SerializedName("private_venue")
        val privateVenue: Boolean,
        @SerializedName("venue_icon")
        val venueIcon: VenueIcon,
        @SerializedName("venue_id")
        val venueId: Int,
        @SerializedName("venue_name")
        val venueName: String,
        @SerializedName("location")
        val venueLocation: Location
)