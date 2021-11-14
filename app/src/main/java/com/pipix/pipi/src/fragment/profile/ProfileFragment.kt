package com.pipix.pipi.src.fragment.profile

import android.os.Bundle
import android.view.View
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentProfileBinding

class ProfileFragment  : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn = binding.button


    }

}