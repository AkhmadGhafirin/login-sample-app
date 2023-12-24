package com.cascer.loginsampleapp.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cascer.loginsampleapp.R
import com.cascer.loginsampleapp.databinding.ViewUserBinding
import com.cascer.loginsampleapp.domain.model.User
import com.cascer.loginsampleapp.utils.ImageUtils.loadCircle

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserViewHolder>(DIFF_USER_ITEM_CALLBACK) {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ViewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class UserViewHolder(private val binding: ViewUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            with(binding) {
                ivUser.loadCircle(binding.root.context, item.avatar)
                tvFullName.text = binding.root.context.getString(
                    R.string.full_name, item.firstName, item.lastName
                )
                tvEmail.text = item.email
            }
        }
    }

    companion object {
        val DIFF_USER_ITEM_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User, newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: User, newItem: User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}