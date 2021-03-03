package com.mvvmstructure.offline.data.remote


import com.mvvmstructure.offline.ui.common.model.UserResponse

interface Api {

    suspend fun getUsers(): ApiResponse<UserResponse>
}
