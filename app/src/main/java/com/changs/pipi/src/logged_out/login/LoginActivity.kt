package com.changs.pipi.src.logged_out.login

import android.os.Bundle
import com.changs.pipi.config.BaseActivity
import com.changs.pipi.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val back = binding.loginImgbtnBack

        back.setOnClickListener {
            finish()
        }
    }
}