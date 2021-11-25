package com.pipix.pipi.src.fragment.logged_out.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.config.ApplicationClass.Companion.sSharedPreferences
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentLoginBinding

class LoginFragment  : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back = binding.loginImgbtnBack
        val userID = binding.loginEdittextId


        val t = binding.loginBtnLogin
        t.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_second_graph)
            findNavController().graph.startDestination = R.id.second_graph

            var id = ""
            id = if(userID.text.isNullOrBlank()) "userID" else userID.text.toString()
            with (sSharedPreferences.edit()) {
                putString(getString(R.string.sharedIDKey), id)
                commit()
            }
        }


    }
}