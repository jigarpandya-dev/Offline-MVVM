package com.mvvmstructure.offline.data.remote


import com.mvvmstructure.offline.BuildConfig
import com.mvvmstructure.offline.ui.common.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager : Api {

    private val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return@lazy Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .client(okHttpClient.build())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getUsers(): ApiResponse<UserResponse> {
        return executeApiHelper { apiService.getUsers() }
    }

}



