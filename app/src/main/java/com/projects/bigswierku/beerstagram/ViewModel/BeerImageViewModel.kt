package com.projects.bigswierku.beerstagram.ViewModel

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.*
import com.projects.bigswierku.beerstagram.toPost
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


class BeerImageViewModel @Inject constructor(private val untappedAPI: UntappedAPI){


    fun getBeerImages() : Single<List<Photo>> = untappedAPI.getBeerInfo().flatMap { Single.just( it.response.beer.medias.items.flatMap { listOf(it.photo) })}



}