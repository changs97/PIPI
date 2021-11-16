package com.pipix.pipi.src.fragment.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = binding.button2
        btn.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_profileFragment) }
    }
}