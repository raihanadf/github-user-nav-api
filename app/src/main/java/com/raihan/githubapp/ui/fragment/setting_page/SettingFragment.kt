package com.raihan.githubapp.ui.fragment.setting_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raihan.githubapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

	private var _binding: FragmentSettingBinding? = null
	private val b get() = _binding!!

	private val themeOptions = listOf("Light", "Dark", "System")
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentSettingBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// [[ Handle onBackPressed on top bar ]]
		b.topBar.apply {
			setNavigationOnClickListener {
				activity?.onBackPressedDispatcher?.onBackPressed()
			}
		}

		b.themeSettingButton.setOnClickListener {
			showThemeDialog()
		}
	}

	private fun showThemeDialog() {
		val builder = MaterialAlertDialogBuilder(requireActivity())
			.setTitle("Theme Preference")
			.setSingleChoiceItems(themeOptions.toTypedArray(), 2) { dialog, which ->
				val selectedTheme = when (which) {
					0 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
					1 -> AppCompatDelegate.MODE_NIGHT_NO
					else -> AppCompatDelegate.MODE_NIGHT_YES
				}
				dialog.dismiss()
				AppCompatDelegate.setDefaultNightMode(selectedTheme)
			}
			.create()
		builder.show()
	}

}