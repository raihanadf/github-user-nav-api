package com.raihan.githubapp.ui.fragment.favorite_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.githubapp.data.factory.ViewModelFactory
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.FragmentFavoriteBinding
import com.raihan.githubapp.ui.adapter.ListUserAdapter

class FavoriteFragment : Fragment() {

	private var _binding: FragmentFavoriteBinding? = null
	private val b get() = _binding!!
	private lateinit var favoriteViewModel: FavoriteViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentFavoriteBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		// [[ Obtain ViewModel ]]
		favoriteViewModel = obtainViewModel(requireActivity())

		setUsersList(b = b)

		// [[ Handle onBackPressed on top bar ]]
		b.topBar.apply {
			setNavigationOnClickListener {
				activity?.onBackPressedDispatcher?.onBackPressed()
			}
		}

		// [[ ----- Viewmodel apply to UI ----- ]]
		favoriteViewModel.apply {
			getAllFavorites().observe(viewLifecycleOwner) { users ->
				val items = arrayListOf<UserItems>()
				users.map {
					val item = UserItems(it.username, it.avatarUrl)
					items.add(item)
				}
				setUsersList(items, b)
			}

			isLoading.observe(viewLifecycleOwner) {
				loadingView(it)
			}
		}
	}

	private fun loadingView(isLoading: Boolean) {
		if (isLoading) {
			b.progressIndicator.visibility = View.VISIBLE
			b.progressIndicator.isIndeterminate = true
		} else {
			b.progressIndicator.setProgressCompat(100, true)
			b.progressIndicator.visibility = View.INVISIBLE
		}
	}

	private fun setUsersList(
		users: List<UserItems?>? = null,
		b: FragmentFavoriteBinding
	) {
		// [[ Initialize list user adapter ]]
		val adapter = ListUserAdapter(true) {
			// [[ Set this to true, cause these are already a favorite duh ]]
			favoriteViewModel.toggleFavorite(it, true)
		}
		val layoutManager = LinearLayoutManager(requireContext())

		// [[ Show text thingy if null ]]
		b.txtNoFav.visibility = View.VISIBLE

		if (!users.isNullOrEmpty()) {
			b.txtNoFav.visibility = View.GONE
			adapter.submitList(users)
		}

		b.rvUsersFavorite.adapter = adapter
		b.rvUsersFavorite.layoutManager = layoutManager
		b.rvUsersFavorite.addItemDecoration(
			DividerItemDecoration(
				requireContext(), layoutManager.orientation
			)
		)
	}

	private fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel {
		val factory = ViewModelFactory.getAppInstance(activity.application)
		return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
	}
}