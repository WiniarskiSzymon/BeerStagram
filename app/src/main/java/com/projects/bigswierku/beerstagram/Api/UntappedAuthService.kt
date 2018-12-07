package com.projects.bigswierku.beerstagram.Api


import com.projects.bigswierku.beerstagram.model.untapped.OAuthToken
import io.reactivex.Single
import retrofit2.http.*

interface UntappedAuthService{


    @FormUrlEncoded
    @POST("oauth/authenticate/")
    fun getCode(
            @Field("client_id")clientID: String,
            @Field("response_type")responseType: String = "code",
            @Field("redirect_url") url : String = ""): Single<OAuthToken>
}