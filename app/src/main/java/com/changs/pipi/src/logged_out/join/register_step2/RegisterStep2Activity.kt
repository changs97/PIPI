package com.changs.pipi.src.logged_out.join.register_step2

import android.os.Bundle
import com.changs.pipi.config.BaseActivity
import com.changs.pipi.databinding.ActivityRegisterStep2Binding

class RegisterStep2Activity : BaseActivity<ActivityRegisterStep2Binding>(
    ActivityRegisterStep2Binding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val back = binding.registerStep2ImgbtnBack

        back.setOnClickListener {
            finish()
        }
    }
}