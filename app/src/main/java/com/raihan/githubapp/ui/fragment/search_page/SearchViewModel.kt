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

    private var _searchedUsers = MutableLiveData<List<UserItems?>?>()
    val searchedUsers: LiveData<List<UserItems?>?> get() = _searchedUsers

    // [[ TODO: can be wrapped into one function, separation from specific and all is just using
    // the name parameter ]]
    fun getUser(name: String) {
        viewModelScope.launch {
            try {
                val result = ApiConfig.getGithubService().searchUser(name)
                _searchedUsers.value = result.items
                Log.d(TAG, "getUser: ${result.items}")
            } catch (e: HttpException) {
                val response = (e as? HttpException)?.response()?.body()
                Log.e(TAG, "Error fetching users: $e", e)
                Log.d(TAG, "JSON Response: $response")
            }
        }
    }

    init {
        getUser("raihanadf")
    }

}