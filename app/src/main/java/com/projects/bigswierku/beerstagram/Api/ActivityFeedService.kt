package com.projects.bigswierku.beerstagram.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityFeedService{



    @GET("checkin/recent")
    fun getActivityFeed(
            @Query("access_token")accessToken : String,
            @Query ("min_id") minId : String = "",
            @Query("max_id") maxId : String = ""

    )
}