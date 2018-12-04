package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class PubLocalRequest(
@SerializedName("meta")
@Transient
val meta: Meta,
@SerializedName("notifications")
@Transient
val notifications: List<Any>,
@SerializedName("response")
val response : PubResponse
)