package com.projects.bigswierku.beerstagram.Api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projects.bigswierku.beerstagram.model.untapped.FriendCheckIn
import com.projects.bigswierku.beerstagram.model.untapped.LocalCheckIn


@Database(entities= arrayOf(LocalCheckIn::class, FriendCheckIn::class),version = 1)
abstract class UntappedDatabase: RoomDatabase(){
    abstract fun localCheckInDao(): LocalCheckInDao
    abstract fun friendCheckInDao() : FriendCheckInDao




}