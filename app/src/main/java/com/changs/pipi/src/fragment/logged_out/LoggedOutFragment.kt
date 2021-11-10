package com.changs.pipi.src.fragment.logged_out

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentLoggedOutBinding


class LoggedOutFragment : BaseFragment<FragmentLoggedOutBinding>(FragmentLoggedOutBinding::bind, R.layout.fragment_logged_out) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginBtn = binding.loggedOutBtnLogin
        val joinBtn = binding.loggedOutBtnJoin

        loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loggedOutFragment_to_loginFragment)
        }

        joinBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loggedOutFragment_to_registerStep1Fragment)
        }


    }
}