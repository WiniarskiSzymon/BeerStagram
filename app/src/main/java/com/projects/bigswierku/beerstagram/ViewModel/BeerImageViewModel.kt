package com.projects.bigswierku.beerstagram.ViewModel

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.*
import com.projects.bigswierku.beerstagram.toPost
import io.reactivex.Flowable
import javax.inject.Inject


class BeerImageViewModel @Inject constructor(val untappedAPI: UntappedAPI){


    fun getBeerImages() : Flowable<Photo> = untappedAPI.getBeerInfo().flatMap { Flowable.fromIterable( it.response.beer.medias.items.flatMap { listOf(it.photo) })}



}