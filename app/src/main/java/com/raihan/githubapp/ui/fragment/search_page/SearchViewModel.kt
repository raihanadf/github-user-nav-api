package com.raihan.githubapp.ui.fragment.search_page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.network.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel : ViewModel() {
	companion object {
		const val TAG = "SearchVM"
	}

	private var _searchedUsers = MutableLiveData<List<UserItems?>?>()
	val searchedUsers: LiveData<List<UserItems?>?> get() = _searchedUsers
	private var _isLoading = MutableLiveData(true)
	val isLoading: LiveData<Boolean> = _isLoading
	private var _isError = MutableLiveData(false)
	val isError: LiveData<Boolean> = _isError


	fun getUser(name: String? = null) {
		_isError.value = false
		_isLoading.value = true
		viewModelScope.launch {
			try {
				val result: List<UserItems?>? = if (name != null) {
					ApiConfig.getGithubService().searchUser(name).items
				} else {
					ApiConfig.getGithubService().getAllUsers()
				}
				_searchedUsers.value = result
				Log.d(TAG, "$result")
			} catch (e: Exception) {
				val response = (e as? HttpException)?.response()?.body()
				Log.e(TAG, "Error fetching users: $e", e)
				Log.d(TAG, "JSON Response: $response")
				_isError.value = true
			}
			_isLoading.value = false
		}
	}

	init {
		getUser()
	}

}