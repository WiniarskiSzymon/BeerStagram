package com.projects.bigswierku.beerstagram.Api

import com.projects.bigswierku.beerstagram.model.untapped.PubLocalRequest
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query



interface  LocalCheckInService {

    @GET("thepub/local")
    fun getPubLocal(
            @Query("client_id") clientID: String,
            @Query("client_secret") clientSecret: String,
            @Query("lat") lat: String,
            @Query("lng") lng: String,
            @Query("dist_pref") dist_pref: String
    ): Flowable<PubLocalRequest>
}