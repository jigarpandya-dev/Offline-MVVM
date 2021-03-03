package com.mvvmstructure.offline.data.local.database

import com.mvvmstructure.offline.ui.common.model.User

interface Database {
    suspend fun getAll(): List<User>

    suspend fun addAll(list: List<User>)

    suspend fun cleanData()
}