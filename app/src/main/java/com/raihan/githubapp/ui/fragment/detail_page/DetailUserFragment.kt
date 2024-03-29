package com.raihan.githubapp.ui.fragment.detail_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.raihan.githubapp.R
import com.raihan.githubapp.databinding.FragmentDetailUserBinding
import com.raihan.githubapp.ui.adapter.SectionsPagerAdapter

class DetailUserFragment : Fragment() {
	companion object {
		private val TAB_TITLES = listOf(
			"Followers",
			"Followings"
		)
	}

	private var _b: FragmentDetailUserBinding? = null
	private val b get() = _b!!
	private val detailViewModel by viewModels<DetailViewModel>()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_b = FragmentDetailUserBinding.inflate(inflater, container, false)
		return b.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// [[ Take arguments from the bundle/safeargs ]]
		val args = DetailUserFragmentArgs.fromBundle(arguments as Bundle)
		val username = args.username
		val avatar = args.userAvatar
		b.userAvatar.load(avatar) {
			placeholder(R.drawable.loading)
			error(R.drawable.ic_broken_image)
		}

		// [[ Handle onBackPressed on top bar ]]
		b.topBar.apply {
			setNavigationOnClickListener {
				activity?.onBackPressedDispatcher?.onBackPressed()
			}
			title = username
		}

		// [[ -------- Viewmodel -------- ]]
		detailViewModel.apply {
			getDetailUser(username)
			detailUser.observe(viewLifecycleOwner) {
				b.userName.text =
					if (it.name.toString() != "null") it.name.toString() else ""
				b.userUsername.text = it.login.toString()
				b.userFollowers.text = resources.getString(
					R.string
						.followers, it.followers
				)
				b.userFollowing.text = resources.getString(
					R.string
						.followers, it.following
				)

				// [[ TabLayout and ViewPager2 ]]
				val sectionsPagerAdapter =
					SectionsPagerAdapter(requireActivity(), username)
				b.viewPager.adapter = sectionsPagerAdapter
				TabLayoutMediator(b.tabs, b.viewPager) { tab, position ->
					tab.text = TAB_TITLES[position]
				}.attach()
			}

			// [[ Handle Linear Progress Bar ]]
			isLoading.observe(viewLifecycleOwner) {
				if (it) {
					b.progressIndicator.visibility = View.VISIBLE
					b.progressIndicator.isIndeterminate = true
				} else {
					b.progressIndicator.setProgressCompat(100, true)
					b.progressIndicator.visibility = View.INVISIBLE
				}
			}

			isError.observe(viewLifecycleOwner) {
				if (it) {
					Snackbar.make(requireView(), "Network Error!", Snackbar
						.LENGTH_SHORT)
						.show()
				}

			}

		}
	}
}