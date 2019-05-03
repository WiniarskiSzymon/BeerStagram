package com.projects.bigswierku.beerstagram.Api

import androidx.room.*
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import io.reactivex.Observable
import io.reactivex.Single

@Dao interface FriendCheckInDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckIn(checkInPost: FriendCheckIn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCheckIns(checkInPosts: List<FriendCheckIn>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCheckIn(checkInPost: FriendCheckIn):Int

    @Delete
    fun deleteCheckIn(checkInPost: FriendCheckIn)

    @Query("select * from friendCheckIns where checkinId = :id")
    fun getCheckInById(id: Int?): Single<FriendCheckIn>

    @Query("select * from friendCheckIns")
    fun getCheckIns() : Observable<List<FriendCheckIn>>

    @Query("delete from friendCheckIns")
    fun deleteAllCheckIns()
 }