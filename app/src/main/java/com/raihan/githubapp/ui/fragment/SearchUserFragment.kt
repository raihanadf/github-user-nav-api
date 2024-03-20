package com.raihan.githubapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.raihan.githubapp.databinding.FragmentSearchUserBinding

class SearchUserFragment : Fragment() {

    private var _b: FragmentSearchUserBinding? = null
    private val b get() = _b!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentSearchUserBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // [[ Test: to detail if works ]]
        b.btnDetail.setOnClickListener {
            val toDetailUser = SearchUserFragmentDirections.actionSearchUserFragmentToDetailUserFragment()
            findNavController().navigate(toDetailUser)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _b = null
    }

}