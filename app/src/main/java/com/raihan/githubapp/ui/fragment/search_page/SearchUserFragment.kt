package com.raihan.githubapp.ui.fragment.search_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.raihan.githubapp.databinding.FragmentSearchUserBinding

class SearchUserFragment : Fragment() {

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

        searchViewModel.getUser("raihanadf")

    }

    override fun onDestroy() {
        super.onDestroy()
        _b = null
    }

}