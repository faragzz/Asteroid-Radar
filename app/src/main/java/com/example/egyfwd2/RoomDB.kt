package com.example.egyfwd2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(version = 1, entities = [AstroidHolder::class], exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract val getAstroidDao: RoomDBDao
    companion object{

        private lateinit var INSTANCE: RoomDB

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): RoomDB {
            kotlinx.coroutines.internal.synchronized(RoomDB::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "room"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
