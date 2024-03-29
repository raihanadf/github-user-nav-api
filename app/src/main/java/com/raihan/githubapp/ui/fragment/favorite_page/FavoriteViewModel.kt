package com.raihan.githubapp.ui.fragment.favorite_page

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.local.UserFavoriteRepository
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : ViewModel() {
	private val mFavoriteRepository: UserFavoriteRepository =
		UserFavoriteRepository(application)

	fun insert(user: UserFavoriteEntity) {
		viewModelScope.launch {
			mFavoriteRepository.insert(user)
		}
	}

	fun delete(user: UserFavoriteEntity) {
		viewModelScope.launch {
			mFavoriteRepository.delete(user)
		}
	}

	companion object {
		const val TAG = "FavoriteVM"
	}
}