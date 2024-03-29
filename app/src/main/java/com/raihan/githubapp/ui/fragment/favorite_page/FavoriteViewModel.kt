package com.raihan.githubapp.ui.fragment.favorite_page

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.local.UserFavoriteRepository
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : ViewModel() {
	private val mFavoriteRepository: UserFavoriteRepository =
		UserFavoriteRepository(application)
	private var _isLoading = MutableLiveData(true)
	val isLoading: LiveData<Boolean> = _isLoading

	fun getAllFavorites(): LiveData<List<UserFavoriteEntity>> {
		_isLoading.value = true
		val user = mFavoriteRepository.getAllFavUser()
		_isLoading.value = false
		return user
	}

	fun toggleFavorite(user: UserFavoriteEntity, isFavorite: Boolean) {
		_isLoading.value = true
		viewModelScope.launch {
			if (!isFavorite) {
				mFavoriteRepository.insert(user)
			} else {
				mFavoriteRepository.delete(user)
			}
			_isLoading.value = false
		}
	}

	companion object {
		const val TAG = "FavoriteVM"
	}
}