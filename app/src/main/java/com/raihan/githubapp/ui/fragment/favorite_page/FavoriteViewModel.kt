package com.raihan.githubapp.ui.fragment.favorite_page

import android.app.Application
import androidx.lifecycle.ViewModel
import com.raihan.githubapp.data.local.UserFavoriteRepository
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity

class FavoriteViewModel(application: Application) : ViewModel() {
	private val mFavoriteRepository: UserFavoriteRepository =
		UserFavoriteRepository(application)

	fun insert(user: UserFavoriteEntity) {
		mFavoriteRepository.insert(user)
	}

	fun delete(user: UserFavoriteEntity) {
		mFavoriteRepository.delete(user)
	}

	fun update(user: UserFavoriteEntity) {
		mFavoriteRepository.update(user)
	}
}