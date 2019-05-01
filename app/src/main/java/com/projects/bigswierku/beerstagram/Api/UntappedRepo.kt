package com.projects.bigswierku.beerstagram.Api

import android.location.Location
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import com.projects.bigswierku.beerstagram.model.untapped.LocalCheckIn
import com.projects.bigswierku.beerstagram.toLocalCheckIn
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UntappedRepo @Inject constructor( private val untappedAPI: UntappedAPI, private val untappedDatabase : UntappedDatabase){

    public fun  getLocalCheckIns(lastId: Int, lastKnownLocation : Location) : Observable<List<LocalCheckIn>> {

            fetchCheckInsToDatabase(lastId, lastKnownLocation)
            return untappedDatabase.localCheckInDao().getLocalCheckIns()
    }


    public fun getMoreCheckIns(lastId: Int, lastKnownLocation : Location){}






    private fun fetchCheckInsToDatabase(lastId: Int, lastKnownLocation : Location){
        untappedAPI.getCheckIns(lastId,lastKnownLocation)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                    untappedDatabase.localCheckInDao().deleteAllCheckIns()
                    untappedDatabase.localCheckInDao().insertAllLocalCheckInPosts(it.response.checkins.items.map { it.toLocalCheckIn() })
                }
            .subscribe()
            }
    }



