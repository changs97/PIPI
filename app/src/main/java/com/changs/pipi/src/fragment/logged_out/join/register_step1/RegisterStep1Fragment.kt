package com.changs.pipi.src.fragment.logged_out.join.register_step1

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentRegisterStep1Binding


class RegisterStep1Fragment : BaseFragment<FragmentRegisterStep1Binding>(FragmentRegisterStep1Binding::bind, R.layout.fragment_register_step1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextBtn = binding.registerStep1BtnNext
        val back = binding.registerStep1ImgbtnBack

        nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerStep1Fragment_to_registerStep2Fragment)
        }
    }
}