package com.raihan.githubapp.ui.fragment.setting_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raihan.githubapp.data.factory.ViewModelPrefsFactory
import com.raihan.githubapp.data.local.preference.SettingPreferences
import com.raihan.githubapp.data.local.preference.dataStore
import com.raihan.githubapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

	private var _binding: FragmentSettingBinding? = null
	private val b get() = _binding!!

	private val themeOptions = listOf("System", "Light", "Dark")

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentSettingBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val settingViewModel = obtainViewModel()

		// [[ Handle onBackPressed on top bar ]]
		b.topBar.apply {
			setNavigationOnClickListener {
				activity?.onBackPressedDispatcher?.onBackPressed()
			}
		}

		settingViewModel.getThemeSetting().observe(viewLifecycleOwner) { checked ->
			Log.d("SAYANG", "onViewCreated: $checked")
			// [[ Assign settingViewModel to showThemeDialog ]]
			b.themeSettingButton.setOnClickListener {
				showThemeDialog(checked, settingViewModel)
			}
			b.themeSettingButton.text = when (checked) {
				0 -> "Follow System"
				1 -> "Light Mode"
				else -> "Dark Mode"
			}
		}
	}

	private fun obtainViewModel(): SettingViewModel {
		val pref = SettingPreferences.getInstance(requireActivity().dataStore)
		return ViewModelProvider(
			this,
			ViewModelPrefsFactory(pref)
		)[SettingViewModel::class.java]
	}

	private fun showThemeDialog(
		checked: Int,
		settingViewModel: SettingViewModel
	) {
		val builder = MaterialAlertDialogBuilder(requireActivity())
			.setTitle("Theme Preference")
			.setSingleChoiceItems(
				themeOptions.toTypedArray(),
				checked
			) { dialog, which ->
				val selectedTheme = when (which) {
					0 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
					1 -> AppCompatDelegate.MODE_NIGHT_NO
					else -> AppCompatDelegate.MODE_NIGHT_YES
				}
				dialog.dismiss()
				settingViewModel.saveThemeSetting(which)
				AppCompatDelegate.setDefaultNightMode(selectedTheme)
			}
			.create()
		builder.show()
	}

}