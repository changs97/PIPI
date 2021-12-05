package com.pipix.pipi.src.fragment.logged_out.join.register_step1

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentRegisterStep1Binding
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.RegisterArg
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.RegisterStep2FragmentDirections
import com.pipix.pipi.src.fragment.profile.ProfileFragmentDirections


class RegisterStep1Fragment : BaseFragment<FragmentRegisterStep1Binding>(FragmentRegisterStep1Binding::bind, R.layout.fragment_register_step1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextBtn = binding.registerStep1BtnNext
        val back = binding.registerStep1ImgbtnBack
        val regex = "^(.+)@(.+)$".toRegex()


        back.setOnClickListener {
            findNavController().popBackStack()
        }

        nextBtn.setOnClickListener {
            val userId = binding.registerStep1EdittextId.text.toString()
            val password = binding.registerStep1Password.text.toString()

            // matches email regex
            if(!userId.matches(regex)){
                showCustomToast("이메일 형식이 올바르지 않습니다")
            }else{
                val action = RegisterStep1FragmentDirections.actionRegisterStep1FragmentToRegisterStep2Fragment(
                    RegisterArg(userId, password)
                )
                Navigation.findNavController(view).navigate(action)
            }

        }
    }
}