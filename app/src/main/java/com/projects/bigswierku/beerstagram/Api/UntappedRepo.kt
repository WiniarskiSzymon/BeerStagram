package com.projects.bigswierku.beerstagram.Api

import android.location.Location
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import com.projects.bigswierku.beerstagram.model.untapped.LocalCheckIn
import com.projects.bigswierku.beerstagram.toFriendCheckIn
import com.projects.bigswierku.beerstagram.toLocalCheckIn
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UntappedRepo @Inject constructor( private val untappedAPI: UntappedAPI, private val untappedDatabase : UntappedDatabase) {

    fun getLocalCheckIns(lastId: Int, lastKnownLocation: Location): Observable<List<LocalCheckIn>> {
        deleteFromDatabase()
        fetchLocalCheckInsToDatabase(lastId, lastKnownLocation)
        return untappedDatabase.localCheckInDao().getLocalCheckIns()
    }

    private fun deleteFromDatabase() = untappedDatabase.localCheckInDao().deleteAllCheckIns()

    private fun fetchLocalCheckInsToDatabase(lastId: Int, lastKnownLocation: Location) {
        untappedAPI.getCheckIns(lastId, lastKnownLocation)
            .subscribeOn(Schedulers.io())
            .subscribe({
                untappedDatabase.localCheckInDao()
                    .insertAllLocalCheckInPosts(it.response.checkins.items.map { it.toLocalCheckIn() })
            },
                {})
    }

    fun getMoreCheckIns(lastId: Int, lastKnownLocation : Location){
        fetchLocalCheckInsToDatabase(lastId, lastKnownLocation)
    }


    fun getFriendsCheckIns( token: String):  Observable<List<FriendCheckIn>>{

       fetchFriendsCheckInsToDatabase(token)
        return untappedDatabase.friendCheckInDao().getCheckIns()

    }



    private fun fetchFriendsCheckInsToDatabase(token : String) {
        untappedAPI.getUserFeed(token)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {untappedDatabase.localCheckInDao().deleteAllCheckIns()
                 untappedDatabase.friendCheckInDao().insertAllCheckIns(it.response.checkins.items.map{it.toFriendCheckIn()})
                },
                {

                })


    }
    }



