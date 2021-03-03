package com.mvvmstructure.offline.data.local.database

import androidx.room.Database
import com.mvvmstructure.offline.ui.common.model.User

@Database(entities = [User::class], version = 3)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun dao(): Dao
}