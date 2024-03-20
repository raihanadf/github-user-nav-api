package com.raihan.githubapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raihan.githubapp.databinding.FragmentDetailUserBinding

class DetailUserFragment : Fragment() {

    private var _b: FragmentDetailUserBinding? = null
    private val b get() = _b!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentDetailUserBinding.inflate(inflater, container, false)
        return b.root
    }
}