package com.raihan.githubapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.ItemRowUserBinding

class ListUserAdapter : ListAdapter<UserItems, ListUserAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItems>() {
            override fun areItemsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.binding.tvTest.text = user.id.toString()
    }
}