package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("category_id")
        val categoryId: String,
        @SerializedName("category_name")
        val categoryName: String,
        @SerializedName("is_primary")
        val isPrimary: Boolean
)