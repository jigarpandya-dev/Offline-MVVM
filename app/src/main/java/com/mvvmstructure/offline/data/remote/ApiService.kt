package com.mvvmstructure.offline.data.remote

import com.mvvmstructure.offline.ui.common.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/?results=100")
    suspend fun getUsers(): Response<UserResponse>

}
