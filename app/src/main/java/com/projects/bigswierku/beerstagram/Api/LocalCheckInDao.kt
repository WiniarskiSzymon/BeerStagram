package com.projects.bigswierku.beerstagram.Api

import androidx.room.*
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import com.projects.bigswierku.beerstagram.model.untapped.LocalCheckIn
import io.reactivex.Observable
import io.reactivex.Single

@Dao interface LocalCheckInDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocalCheckInPost(imagePost: LocalCheckIn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLocalCheckInPosts(imagePosts: List<LocalCheckIn>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLocalCheckInPost(imagePost: LocalCheckIn):Int

    @Delete
    fun deleteLocalCheckInPost(imagePost: LocalCheckIn)

    @Query ("select * from localCheckIns where checkinId = :id")
    fun getLocalCheckInById(id: Int?): Single<LocalCheckIn>

    @Query("select * from localCheckIns")
    fun getLocalCheckIns() : Observable<List<LocalCheckIn>>

    @Query("delete from localCheckIns")
    fun deleteAllCheckIns()

}