package com.raihan.githubapp.ui.fragment.detail_page.follow_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.githubapp.data.model.UserItems
import com.raihan.githubapp.databinding.FragmentFollowBinding
import com.raihan.githubapp.ui.adapter.ListUserAdapter

class FollowFragment : Fragment() {
	private var _b: FragmentFollowBinding? = null
	private val b get() = _b!!
	private val followViewModel by viewModels<FollowViewModel>()

	companion object {
		val FOLLOW_TYPE = listOf(
			"FOLLOWER",
			"FOLLOWING"
		)
		const val ARGS_TYPE = "TYPE_FOLLOW"
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_b = FragmentFollowBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setUsersList()

		val username = arguments?.getString("username", "").toString()
		when (arguments?.getString(ARGS_TYPE)) {
			FOLLOW_TYPE[0] -> {
				followViewModel.getFollowers(username)
			}

			FOLLOW_TYPE[1] -> {
				followViewModel.getFollowing(username)
			}
		}
		followViewModel.apply {
			userFollow.observe(viewLifecycleOwner) {
				setUsersList(it)
			}
			isLoading.observe(viewLifecycleOwner) {
				loadingView(it)
			}
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

	private fun setUsersList(users: List<UserItems?>? = null) {
		// [[ Initialize list user adapter ]]
		val adapter = ListUserAdapter()
		val layoutManager = LinearLayoutManager(requireContext())

		if (users != null) {
			adapter.submitList(users)
		}

		b.rvUsers.adapter = adapter
		b.rvUsers.layoutManager = layoutManager
		b.rvUsers.addItemDecoration(
			DividerItemDecoration(
				requireContext(), layoutManager.orientation
			)
		)
	}

}