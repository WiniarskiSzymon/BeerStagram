package com.projects.bigswierku.beerstagram.model.untapped

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("photo_img_lg")
        private val _photoImgLg: String? = "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_100x100.jpg",
        @SerializedName("photo_img_md")
        private val _photoImgMd: String?  = "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_320x320.jpg",
        @SerializedName("photo_img_og")
        private val _photoImgOg: String? = "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_640x640.jpg",
        @SerializedName("photo_img_sm")
        private val _photoImgSm: String? = "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_raw.jpg"
){
        val photoImgLg
                get() = _photoImgLg ?: "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_100x100.jpg"

        val photoImgMd
                get() = _photoImgMd ?: "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_320x320.jpg"

        val photoImgOg
                get() = _photoImgOg ?: "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_640x640.jpg"

        val photoImgSm
                get() = _photoImgSm ?: "https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_raw.jpg"


}
