package com.raihan.githubapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.raihan.githubapp.data.local.preference.SettingPreferences
import kotlinx.coroutines.launch

class MainViewModel(private val pref: SettingPreferences) : ViewModel
	() {
	fun getThemeSetting(): LiveData<Int> {
		return pref.getThemeSetting().asLiveData()
	}

	fun saveThemeSetting(currentTheme: Int) {
		viewModelScope.launch {
			pref.saveThemeSetting(currentTheme)
		}
	}
}
