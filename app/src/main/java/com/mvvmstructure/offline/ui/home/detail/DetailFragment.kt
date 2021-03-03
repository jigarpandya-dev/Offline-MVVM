package com.mvvmstructure.offline.ui.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvmstructure.offline.databinding.FragmentDetailBinding
import com.mvvmstructure.offline.ui.common.base.BaseFragment
import com.mvvmstructure.offline.ui.common.model.User
import com.mvvmstructure.offline.ui.home.HomeAdapter

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun onViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        requireArguments().getString(HomeAdapter.INTENT_EXTRA_USER)?.let {
            binding.nameTextView.text = it
        }
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }
}