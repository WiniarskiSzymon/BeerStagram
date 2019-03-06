package com.projects.bigswierku.beerstagram.Api

import com.projects.bigswierku.beerstagram.model.untapped.BeerInfoRequest
import com.projects.bigswierku.beerstagram.model.untapped.PubLocalRequest
import com.projects.bigswierku.beerstagram.model.untapped.UserFeedResponse
import com.projects.bigswierku.beerstagram.model.untapped.TokenResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface  UntappedService{

    @GET("beer/info/1629")
    fun getBeerInfo (
            @Query("client_id")clientID: String,
            @Query("client_secret")clientSecret : String
    ): Single<BeerInfoRequest>

    @GET("thepub/local")
    fun getPubLocal (
            @Query("client_id")clientID: String,
            @Query("client_secret")clientSecret : String,
            @Query("lat")lat : String,
            @Query("lng")lng : String,
            @Query("dist_pref") dist_pref : String,
            @Query("max_id")lastId : String
    ):Single<PubLocalRequest>

     @GET
     fun getAuthorizationToken(
         @Url url:String,
         @Query("client_id")clientID: String,
         @Query("client_secret")clientSecret : String,
         @Query("response_type")responseType :String,
         @Query("redirect_url")redirectURL : String,
         @Query("code")code:String
     ):Single<TokenResponse>

    @GET("checkin/recent")
    fun getUserFeed(
        @Query("access_token")token : String
    ) : Single<UserFeedResponse>
}
