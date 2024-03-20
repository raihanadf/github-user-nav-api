package com.raihan.githubapp.ui.fragment.search_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.shape.MaterialShapeDrawable
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentSearchUserBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.appbarLayout.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(context)

        searchViewModel.searchedUsers.observe(viewLifecycleOwner) {
            setUsersData(it.items)
        }
    }

    private fun setUsersData(users: List<UserItems?>?) {
        Log.d(TAG, "setUsersData: $users")
        val adapter = ListUserAdapter()
        adapter.submitList(users)
        b.rvUsers.adapter = adapter
        b.rvUsers.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _b = null
    }

}