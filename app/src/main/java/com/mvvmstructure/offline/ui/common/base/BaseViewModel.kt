package com.mvvmstructure.offline.ui.common.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun clearLiveData()
}