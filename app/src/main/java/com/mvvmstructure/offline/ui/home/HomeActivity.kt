package com.mvvmstructure.offline.ui.home


import com.mvvmstructure.offline.databinding.ActivityHomeBinding
import com.mvvmstructure.offline.ui.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        // Binding object with view
        binding.navHostFragment
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }

    override fun onResume() {
        super.onResume()

    }

}
