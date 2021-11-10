package com.changs.pipi.src.fragment.logged_out.join.register_step2

import android.os.Bundle
import android.view.View
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentRegisterStep2Binding

class RegisterStep2Fragment : BaseFragment<FragmentRegisterStep2Binding>(
    FragmentRegisterStep2Binding::bind, R.layout.fragment_register_step2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back = binding.registerStep2ImgbtnBack


    }
}