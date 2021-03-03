package com.mvvmstructure.offline.data.remote

class ApiResponse<out T>(val status: Status,val data : T? ,val message : String?) {

    companion object{
        fun <T> success(data : T?) : ApiResponse<T> {
            return ApiResponse(Status.SUCCESS,data,null)
        }

        fun <T> error(message: String?) : ApiResponse<T> {
            return ApiResponse(Status.ERROR,null,message)
        }

        fun <T> noInternet(message: String?) : ApiResponse<T> {
            return ApiResponse(Status.NO_INTERNET,null,message)
        }

        fun <T> loading(data : T?) : ApiResponse<T> {
            return ApiResponse(Status.LOADING,data,null)
        }
    }
}
