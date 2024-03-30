package com.raihan.githubapp.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raihan.githubapp.data.local.preference.SettingPreferences
import com.raihan.githubapp.ui.MainViewModel
import com.raihan.githubapp.ui.fragment.setting_page.SettingViewModel

class ViewModelPrefsFactory(private val pref: SettingPreferences) :
	ViewModelProvider.NewInstanceFactory() {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
			return SettingViewModel(pref) as T
		} else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(pref) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
	}
}
