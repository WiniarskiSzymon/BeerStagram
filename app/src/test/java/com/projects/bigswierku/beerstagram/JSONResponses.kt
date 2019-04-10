package com.projects.bigswierku.beerstagram

class JSONResponses(){


    companion object {
        val localCheckInsJSON =  ClassLoader.getSystemClassLoader().getResource("LocalCheckIns.json").readText()
        val beerInfoJSON = ClassLoader.getSystemClassLoader().getResource("BeerInfo.json").readText()
        val beerSearchJSON = ClassLoader.getSystemClassLoader().getResource("SearchBeer.json").readText()
    }
}