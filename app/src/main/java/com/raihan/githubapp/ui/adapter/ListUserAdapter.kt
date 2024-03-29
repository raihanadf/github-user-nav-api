package com.raihan.githubapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.raihan.githubapp.R
import com.raihan.githubapp.UserNavigationDirections
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.ItemRowUserBinding

class ListUserAdapter(
	val isFavoriteList: Boolean, private val onFavoriteClick:
		(UserFavoriteEntity) -> Unit = {}
) :
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
		// [[ Use this variable to not make another adapter for favorite ]]
		if (isFavoriteList) {
			holder.binding.buttonOpen.visibility = View.GONE
		} else {
			holder.binding.buttonOpenFav.visibility = View.GONE
			holder.binding.buttonUnfav.visibility = View.GONE
		}

		val user = getItem(position)
		holder.binding.apply {
			imageAvatar.load(user.avatarUrl) {
				placeholder(R.drawable.loading)
				error(R.drawable.ic_broken_image)
			}
			username.text = user.login.toString()
			buttonOpen.setOnClickListener { toDetailUser(holder, user) }
			buttonOpenFav.setOnClickListener { toDetailUser(holder, user) }
		}

		// [[ UserItems to UserFavEntity, and then assign the button ofc ]]
		val userFav = UserFavoriteEntity(user.login.toString(), user.avatarUrl)
		holder.binding.buttonUnfav.setOnClickListener { onFavoriteClick(userFav) }

		// [[ OnClickListener to Detail ]]
		holder.itemView.setOnClickListener { toDetailUser(holder, user) }
	}

	private fun toDetailUser(holder: ViewHolder, user: UserItems) {
		val direction = UserNavigationDirections.actionGlobalDetailUserFragment(
			username = user.login.toString(),
			userAvatar = user.avatarUrl.toString()
		)
		holder.itemView.findNavController().navigate(direction)
	}
}