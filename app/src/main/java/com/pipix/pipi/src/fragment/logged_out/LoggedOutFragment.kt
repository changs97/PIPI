package com.pipix.pipi.src.fragment.logged_out

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentLoggedOutBinding
import com.pipix.pipi.src.main.MainActivity


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
    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val activity = getActivity() as MainActivity
                activity.finish()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}