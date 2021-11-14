package com.changs.pipi.src.fragment.discover

import android.os.Bundle
import android.view.View
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentDiscoverBinding
import com.changs.pipi.src.fragment.home.HomeFragment
import com.changs.pipi.src.fragment.plus.PlusFragment
import com.changs.pipi.src.fragment.search.SearchFragment

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>(FragmentDiscoverBinding::bind, R.layout.fragment_discover) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = binding.bottomNav

        val homeFragment = HomeFragment()
        val plusFragment = PlusFragment()
        val searchFragment = SearchFragment()

        bottomNav


    }
}