package com.projects.bigswierku.beerstagram

import com.google.gson.GsonBuilder
import com.projects.bigswierku.beerstagram.model.untapped.*

class DataHelper(){


    companion object {
        private val gson = GsonBuilder()
        .registerTypeAdapterFactory(SingletonListTypeAdapterFactory())
        .setLenient()
        .create()
        val localCheckInsJSON =  ClassLoader.getSystemClassLoader().getResource("LocalCheckIns.json").readText()
        val beerInfoJSON = ClassLoader.getSystemClassLoader().getResource("BeerInfo.json").readText()
        val beerSearchJSON = ClassLoader.getSystemClassLoader().getResource("SearchBeer.json").readText()
        val tokenJSON = ClassLoader.getSystemClassLoader().getResource("token.json").readText()

        val  checkInRespons = gson.fromJson(localCheckInsJSON, PubLocalRequest::class.java)
        val  beerInfoResponse = gson.fromJson(beerInfoJSON, BeerInfoRequest::class.java)
        val  beerSearchResponse = gson.fromJson(beerSearchJSON, BeerSearchResponse::class.java)
        val  tokenResponse = gson.fromJson(tokenJSON,TokenResponse::class.java)
    }
}