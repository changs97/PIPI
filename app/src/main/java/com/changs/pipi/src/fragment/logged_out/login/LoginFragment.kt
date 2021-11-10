package com.changs.pipi.src.fragment.logged_out.login

import android.os.Bundle
import android.view.View
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentLoginBinding

class LoginFragment  : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back = binding.loginImgbtnBack


    }
}