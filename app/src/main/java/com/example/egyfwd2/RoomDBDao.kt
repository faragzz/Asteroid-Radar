package com.example.egyfwd2

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.internal.synchronized

@Dao
interface RoomDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertData(x: List<AstroidHolder>)

    @Query("SELECT * FROM asteroid_table ORDER BY DATE ASC")
     fun getDetails(): Flow<List<AstroidHolder>>

    @Query("SELECT * FROM asteroid_table WHERE date like (:date)")
    fun getToday(date:String): Flow<List<AstroidHolder>>

    @Query("DELETE FROM asteroid_table")
     fun deleteAll()

}


