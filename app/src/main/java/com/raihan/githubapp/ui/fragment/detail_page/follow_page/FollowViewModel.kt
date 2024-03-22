package com.raihan.githubapp.ui.fragment.detail_page.follow_page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.network.ApiConfig
import kotlinx.coroutines.launch

class FollowViewModel : ViewModel() {
	companion object {
		const val TAG = "FollVM"
	}

	private var _userFollow = MutableLiveData<List<UserItems>>()
	val userFollow: LiveData<List<UserItems>> get() = _userFollow
	private var _isLoading = MutableLiveData<Boolean>()
	val isLoading: LiveData<Boolean> get() = _isLoading

	fun getFollowing(username: String) {
		_isLoading.value = true
		viewModelScope.launch {
			try {
				val users = ApiConfig.getGithubService().getFollowing(username)
				_userFollow.value = users
			} catch (e: Exception) {
				Log.d(TAG, "getFollowing: ${e.message}")
			}
			_isLoading.value = false
		}
	}

	fun getFollowers(username: String) {
		_isLoading.value = true
		viewModelScope.launch {
			try {
				val users = ApiConfig.getGithubService().getFollowers(username)
				_userFollow.value = users
			} catch (e: Exception) {
				Log.d(TAG, "getFollowers: ${e.message}")
			}
			_isLoading.value = false
		}
	}
}