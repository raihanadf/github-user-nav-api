package com.raihan.githubapp.ui.fragment.search_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.githubapp.R
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.FragmentSearchUserBinding
import com.raihan.githubapp.ui.adapter.ListUserAdapter

class SearchUserFragment : Fragment() {
	companion object {
		const val TAG = "MainActivity"
	}

	private var _b: FragmentSearchUserBinding? = null
	private val b get() = _b!!

	private val searchViewModel by viewModels<SearchViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_b = FragmentSearchUserBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(b) {
			// [[ Search bar and view thingy ]]
			searchBar.inflateMenu(R.menu.menu_action)
			searchView.setupWithSearchBar(searchBar)
			searchView.editText.setOnEditorActionListener { _, _, _ ->
				searchBar.setText(searchView.text)
				if (searchBar.text.length > 0) {
					searchViewModel.getUser(searchBar.text.toString())
				} else {
					searchViewModel.getUser()
				}
				searchView.hide()
				false
			}

			// [[ Handle back button on Search View ]]
			activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object :
				OnBackPressedCallback(true) {
				override fun handleOnBackPressed() {
					searchView.hide()
				}
			})
		}

		// [[ ViewModel Observe ]]
		searchViewModel.apply {
			// [[ Observe: Searched User ]]
			searchedUsers.observe(viewLifecycleOwner) {
				setUsersList(it)
			}
			// [[ Observe: Is it loading? ]]
			isLoading.observe(viewLifecycleOwner) {
				val pFirst = b.progressIndicator
				if (it) {
					pFirst.visibility = View.VISIBLE
					pFirst.isIndeterminate = true
				} else {
					pFirst.setProgressCompat(100, true)
					pFirst.visibility = View.INVISIBLE
				}
			}
		}

	}

	private fun setUsersList(users: List<UserItems?>?) {
		// [[ Initialize list user adapter ]]
		val adapter = ListUserAdapter()
		val layoutManager = LinearLayoutManager(requireContext())
		adapter.submitList(users)
		b.rvUsers.adapter = adapter
		b.rvUsers.layoutManager = layoutManager
		b.rvUsers.addItemDecoration(
			DividerItemDecoration(
				requireContext(), layoutManager.orientation
			)
		)
	}

	override fun onDestroy() {
		super.onDestroy()
		_b = null
	}

}