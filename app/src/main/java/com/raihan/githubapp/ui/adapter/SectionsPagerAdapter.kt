package com.raihan.githubapp.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.raihan.githubapp.ui.fragment.detail_page.follow_page.FollowFragment

class SectionsPagerAdapter(fm: FragmentActivity, val username: String) :
	FragmentStateAdapter(fm) {
	override fun getItemCount(): Int {
		return 2
	}

	override fun createFragment(position: Int): Fragment {
		var fragment = FollowFragment()
		fragment?.arguments = Bundle().apply {
			putString("username", username)
			putString(
				FollowFragment.ARGS_TYPE,
				FollowFragment.FOLLOW_TYPE[position]
			)
		}
		return fragment
	}
}