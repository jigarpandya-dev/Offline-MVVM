package com.mvvmstructure.offline.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvmstructure.offline.ui.common.model.User

@Dao
interface Dao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<User>)

    @Query("DELETE FROM user")
    fun cleanData()

}