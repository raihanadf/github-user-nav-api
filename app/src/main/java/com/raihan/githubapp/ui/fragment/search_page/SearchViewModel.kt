package com.raihan.githubapp.ui.fragment.search_page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.data.model.Users
import com.raihan.githubapp.data.network.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel : ViewModel() {
    companion object {
        const val TAG = "SearchVM"
    }

    private var _users = MutableLiveData<List<UserItems>>()
    private val users: LiveData<List<UserItems>> get() = _users

    private var _userResults = MutableLiveData<Users>()
    private val userResult: LiveData<Users> get() = _userResults

    fun getUser(name: String) {
        viewModelScope.launch {
            try {
                val users = ApiConfig.getGithubService().searchUser(name)
                _userResults.value = users
                Log.d(TAG, "getUser: ${userResult.value}")
            } catch (e: HttpException) {
                val response = (e as? HttpException)?.response()?.body()
                Log.e(TAG, "Error fetching users: $e", e)
                Log.d(TAG, "JSON Response: $response")
            }
        }
    }

    fun getAllUser() {
        viewModelScope.launch {
            try {
                val users = ApiConfig.getGithubService().getAllUsers()
                _users.value = users
                Log.d(TAG, "getUser: ${userResult.value}")
            } catch (e: Exception) {
                Log.d(TAG, "$e")
            }
        }
    }

}