package com.mvvmstructure.offline.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvmstructure.offline.R
import com.mvvmstructure.offline.data.remote.Status
import com.mvvmstructure.offline.databinding.FragmentHomeBinding
import com.mvvmstructure.offline.ui.common.base.BaseFragment
import com.mvvmstructure.offline.ui.common.model.User
import com.mvvmstructure.offline.utils.Helper
import com.mvvmstructure.offline.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private  var homeAdapter: HomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        homeAdapter = HomeAdapter(arrayListOf(), requireActivity(), userClickListener)
        binding.recyclerView?.adapter = homeAdapter
        binding.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        homeViewModel.getUsersFromLocal()

    }

    override fun initListener() {

    }

    override fun initObserver() {
        homeViewModel.apiStateLocal.observe(this, Observer {
            it?.let { res ->
                when (res.status) {
                    Status.SUCCESS ->
                        if (it.data?.size == 0) { // if no data from local db, fetch data from server after checking internet connection
                            if (Helper.isOnline(requireContext())) {
                                homeViewModel.getUsersFromServer()
                                binding.progress.visibility = View.VISIBLE
                            }
                            else
                                showToast(getString(R.string.no_internet))
                        } else {
                            homeAdapter?.addData((it.data as ArrayList<User>?)!!)
                        }

                    else -> showToast(it.message!!)
                }
            }
        })

        homeViewModel.apiStateServer.observe(this, Observer {
            it?.let { res ->
                when (res.status) {
                    Status.SUCCESS -> {
                        binding.progress.visibility = View.GONE
                        if (it.data?.size == 0) {
                            showToast(getString(R.string.no_data))
                        } else
                            homeAdapter?.addData((it.data as ArrayList<User>?)!!)
                      }

                    Status.ERROR -> {
                        showToast(res.message!!)
                        binding.progress.visibility= View.VISIBLE
                    }
                    else -> showToast(res.message!!)
                }
            }
        })
    }

    private val userClickListener = object : HomeAdapter.onUserClickListener {
        override fun onClick(user: User) {
            //Navigate screen example
            navigate(R.id.action_homeFragment_to_detailFragment) {
                // Add bundle arguments
                user.name?.let { name ->
                    this.putString(
                        HomeAdapter.INTENT_EXTRA_USER,
                        name.title?.plus(" ").plus(name.first).plus(" ").plus(name.last)
                    )

                }

            }
        }

    }
}