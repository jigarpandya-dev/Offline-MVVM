package com.mvvmstructure.offline.ui.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mvvmstructure.offline.utils.extention.hideKeyboard


abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: B
    abstract fun onViewBinding(): B
    abstract fun initView()
    abstract fun initListener()
    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = onViewBinding()
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    override fun onClick(v: View?) {
        hideKeyboard(v)
    }


}