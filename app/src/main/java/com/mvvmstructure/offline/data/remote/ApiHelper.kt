package com.mvvmstructure.offline.data.remote

import retrofit2.Response
import java.net.UnknownHostException

inline fun <T> executeApiHelper(responseMethod: () -> Response<T>): ApiResponse<T> {
    return try {
        val response = responseMethod.invoke()
        when (response.code()) {
            in 200..300 -> {
                val responseBody = response.body()
                if (responseBody != null) {
                    ApiResponse.success(responseBody)
                } else ApiResponse.error("The application has encountered an unknown error.")
            }
            else -> ApiResponse.error("Unexpected internal server error.")
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        ApiResponse.error("Unexpected internal server error.")
    }
}
