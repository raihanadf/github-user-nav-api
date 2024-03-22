package com.raihan.githubapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.raihan.githubapp.R
import com.raihan.githubapp.UserNavigationDirections
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.ItemRowUserBinding

class ListUserAdapter :
	ListAdapter<UserItems, ListUserAdapter.ViewHolder>(DIFF_CALLBACK) {
	companion object {
		val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItems>() {
			override fun areItemsTheSame(
				oldItem: UserItems, newItem: UserItems
			): Boolean {
				return oldItem == newItem
			}

			override fun areContentsTheSame(
				oldItem: UserItems, newItem: UserItems
			): Boolean {
				return oldItem == newItem
			}

		}
	}

	class ViewHolder(val binding: ItemRowUserBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemRowUserBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val user = getItem(position)
		holder.binding.apply {
			imageAvatar.load(user.avatarUrl) {
				placeholder(R.drawable.loading)
				placeholder(R.drawable.ic_broken_image)
			}
			username.text = user.login.toString()
			buttonOpen.setOnClickListener {
				toDetailUser(holder, user)
			}
		}

		// [[ OnClickListener to Detail ]]
		holder.itemView.setOnClickListener {
			toDetailUser(holder, user)
		}
	}

	private fun toDetailUser(holder: ViewHolder, user: UserItems) {
		val direction = UserNavigationDirections.actionGlobalDetailUserFragment(
			username = user.login.toString(),
			userAvatar = user.avatarUrl.toString()
		)
		holder.itemView.findNavController().navigate(direction)
	}
}