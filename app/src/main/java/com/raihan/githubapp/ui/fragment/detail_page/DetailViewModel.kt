package com.raihan.githubapp.ui.fragment.detail_page

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.local.UserFavoriteRepository
import com.raihan.githubapp.data.local.entity.UserFavoriteEntity
import com.raihan.githubapp.data.model.UserDetail
import com.raihan.githubapp.data.network.ApiConfig
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : ViewModel() {
	// [[ Accessing favorite repository, hell this is confusing ]]
	private val mFavoriteRepository: UserFavoriteRepository =
		UserFavoriteRepository(application)

	private var _detailUser = MutableLiveData<UserDetail>()
	val detailUser: LiveData<UserDetail> get() = _detailUser
	private var _isLoading = MutableLiveData(true)
	val isLoading: LiveData<Boolean> = _isLoading
	private var _isError = MutableLiveData<Boolean>()
	val isError: LiveData<Boolean> = _isError

	fun getDetailUser(username: String) {
		_isLoading.value = true
		viewModelScope.launch {
			_isError.value = false
			try {
				val user = ApiConfig.getGithubService().getUserDetail(username)
				_detailUser.value = user
			} catch (e: Exception) {
				Log.d(TAG, "getDetailUser: ${e.message}")
				_isError.value = true
			}
			_isLoading.value = false
		}
	}

	fun toggleFavorite(user: UserFavoriteEntity, isFavorite: Boolean) {
		viewModelScope.launch {
			if (!isFavorite) {
				mFavoriteRepository.insert(user)
			} else {
				mFavoriteRepository.delete(user)
			}
		}
	}

	fun getFavorite(username: String): LiveData<UserFavoriteEntity> =
		mFavoriteRepository.getFavorite(username)

	companion object {
		const val TAG = "DetailVM"
	}

}