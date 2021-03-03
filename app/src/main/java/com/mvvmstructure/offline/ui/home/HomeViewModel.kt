package com.mvvmstructure.offline.ui.home


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvmstructure.offline.data.local.database.Database
import com.mvvmstructure.offline.data.remote.Api
import com.mvvmstructure.offline.data.remote.ApiResponse
import com.mvvmstructure.offline.data.remote.Status
import com.mvvmstructure.offline.ui.common.base.BaseViewModel
import com.mvvmstructure.offline.ui.common.model.User
import com.mvvmstructure.offline.ui.common.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel @ViewModelInject constructor(
    private val api: Api,
    private val database: Database
) : BaseViewModel() {

    private val _apiStateLocal = MutableLiveData<ApiResponse<List<User>>>()
    val apiStateLocal: LiveData<ApiResponse<List<User>>>
        get() = _apiStateLocal

    private val _apiStateServer = MutableLiveData<ApiResponse<List<User>>>()
    val apiStateServer: LiveData<ApiResponse<List<User>>>
        get() = _apiStateServer


    //fetches data from local db then fetches data from server and updates in local db
    fun getUsersFromLocal() {
        viewModelScope.launch {
            var userData = database.getAll()
            _apiStateLocal.value =  ApiResponse.success(userData)
            clearLiveData()

            // because if data base is empty, we are fetching data from observer in Home fragment
            if(userData.size>0){
                val res = api.getUsers()
                if(res.status==Status.SUCCESS){
                    userData = res.data?.results as List<User>
                    withContext(Dispatchers.IO){
                        database.cleanData()
                        database.addAll(userData)
                    }
                }
            }


        }
    }

    //fetches data from server when there's no local data
    fun getUsersFromServer(){
        viewModelScope.launch {
            val res = api.getUsers()
            if(res.status==Status.SUCCESS){
                val userData = res.data?.results as List<User>
                _apiStateServer.value =  ApiResponse.success(userData)
                clearLiveData()
                withContext(Dispatchers.IO){
                    database.cleanData()
                    database.addAll(userData)
                }
            }
            else{
                _apiStateServer.value =  ApiResponse.error(res.message)
            }

        }

    }

    override fun clearLiveData() {
        _apiStateLocal.value = null
        _apiStateServer.value = null
    }
}