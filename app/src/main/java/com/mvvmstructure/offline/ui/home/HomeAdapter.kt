package com.mvvmstructure.offline.ui.home


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mvvmstructure.offline.R
import com.mvvmstructure.offline.databinding.ListItemBinding
import com.mvvmstructure.offline.ui.common.model.User



class HomeAdapter(private val list: ArrayList<User?>,private val context:Activity,private val listener:onUserClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val INTENT_EXTRA_USER = "INTENT_EXTRA_USER"
    }

    fun addData(userData:ArrayList<User>){
        list.clear()
        list.addAll(userData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var inflator = LayoutInflater.from(context)
        return UserViewHolder(ListItemBinding.inflate(inflator))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    inner class UserViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User?) = user?.run {
            binding.name.text = name?.title?.plus(" ").plus(name?.first).plus(" ").plus(name?.last)
            binding.email.text = email
            Glide.with(context)
                 .load(user.picture?.medium)
                 .placeholder(R.mipmap.ic_launcher)
                 .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                 .into(binding.profile)

            binding.root.setOnClickListener {
                listener.onClick(user)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface onUserClickListener{
        fun onClick(user:User)
    }

}