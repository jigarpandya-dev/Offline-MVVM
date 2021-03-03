package com.mvvmstructure.offline.data.local.database

import androidx.room.Query
import com.mvvmstructure.offline.ui.common.model.User

class DatabaseManager(roomDatabase: RoomDatabase) : Database {

    private val dao = roomDatabase.dao()

    override suspend fun getAll(): List<User> {
        return dao.getAll()
    }

    override suspend fun addAll(list: List<User>) {

        dao.addAll(list)
    }

    override suspend fun cleanData() {
        dao.cleanData()
    }


}