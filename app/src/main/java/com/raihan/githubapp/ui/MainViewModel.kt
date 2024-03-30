package com.raihan.githubapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raihan.githubapp.data.local.preference.SettingPreferences

class MainViewModel(private val pref: SettingPreferences) : ViewModel
	() {
	fun getThemeSetting(): LiveData<Int> {
		return pref.getThemeSetting().asLiveData()
	}
}
