package com.raihan.githubapp.ui.fragment.search_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.githubapp.R
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.FragmentSearchUserBinding
import com.raihan.githubapp.ui.adapter.ListUserAdapter

class SearchUserFragment : Fragment() {

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
			searchBar.setOnMenuItemClickListener { item ->
				when (item.itemId) {

					R.id.action_favorite -> {
						Toast.makeText(
							requireActivity(), "Not Implemented Yet", Toast
								.LENGTH_SHORT
						).show()
						true
					}

					R.id.action_settings -> {
						val navigation =
							SearchUserFragmentDirections.actionSearchUserFragmentToSettingFragment()
						findNavController().navigate(navigation)
						true
					}

					else -> true
				}
			}

			// [[ Handle back button on Search View ]]
			handleBackButton()

			// [[ Editor Listener ]]
			searchView.editText.setOnEditorActionListener { _, _, _ ->
				searchBar.setText(searchView.text)
				if (searchBar.text.isNotEmpty()) {
					searchViewModel.getUser(searchBar.text.toString())
				} else {
					searchViewModel.getUser()
				}
				searchView.hide()
				false
			}
		}

		// [[ ViewModel Observe ]]
		searchViewModel.apply {
			// [[ Observe: Searched User ]]
			searchedUsers.observe(viewLifecycleOwner) {
				setUsersList(it)
			}
			// [[ Observe: Is it error? ]]
			isError.observe(viewLifecycleOwner) {
				errorView(it)
			}
			// [[ Observe: Is it loading? ]]
			isLoading.observe(viewLifecycleOwner) {
				loadingView(it)
			}
		}
	}

	private fun errorView(isError: Boolean?) {
		if (isError!!) {
			b.networkError.visibility = View.VISIBLE
			b.rvUsers.visibility = View.INVISIBLE
		} else {
			b.networkError.visibility = View.INVISIBLE
			b.rvUsers.visibility = View.VISIBLE
		}
	}

	private fun loadingView(isLoading: Boolean?) {
		if (isLoading!!) {
			b.progressIndicator.visibility = View.VISIBLE
			b.progressIndicator.isIndeterminate = true
		} else {
			b.progressIndicator.setProgressCompat(100, true)
			b.progressIndicator.visibility = View.INVISIBLE
		}
	}

	private fun handleBackButton() {
		activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object :
			OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				if (b.searchView.isShowing) {
					b.searchView.hide()
				} else {
					this.remove()
					activity?.onBackPressedDispatcher?.onBackPressed()
				}
			}
		}
		)
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