package com.raihan.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.raihan.githubapp.data.factory.ViewModelPrefsFactory
import com.raihan.githubapp.data.local.preference.SettingPreferences
import com.raihan.githubapp.data.local.preference.dataStore
import com.raihan.githubapp.databinding.ActivityMainBinding
import com.raihan.githubapp.ui.fragment.setting_page.SettingViewModel

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		installSplashScreen()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val mainViewModel = obtainViewModel()
		setSavedTheme(mainViewModel)
	}

	private fun setSavedTheme(mainViewModel: SettingViewModel) {
		mainViewModel.getThemeSetting().observe(this) {
			val selectedTheme = when (it) {
				0 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
				1 -> AppCompatDelegate.MODE_NIGHT_NO
				else -> AppCompatDelegate.MODE_NIGHT_YES
			}
			AppCompatDelegate.setDefaultNightMode(selectedTheme)
		}
	}

	private fun obtainViewModel(): SettingViewModel {
		val pref = SettingPreferences.getInstance(application.dataStore)
		return ViewModelProvider(
			this,
			ViewModelPrefsFactory(pref)
		)[SettingViewModel::class.java]
	}

}