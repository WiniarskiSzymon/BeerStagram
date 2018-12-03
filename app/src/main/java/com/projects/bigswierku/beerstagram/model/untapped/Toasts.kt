package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Toasts(
        @SerializedName("auth_toast")
        val authToast: Boolean,
        @SerializedName("count")
        val count: Int,
        @SerializedName("items")
        val items: List<Any>,
        @SerializedName("total_count")
        val totalCount: Int
)