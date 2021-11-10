package com.changs.pipi.src.logged_out.join.register_step1

import android.content.Intent
import android.os.Bundle
import com.changs.pipi.config.BaseActivity
import com.changs.pipi.databinding.ActivityRegisterStep1Binding
import com.changs.pipi.src.logged_out.join.register_step2.RegisterStep2Activity

class RegisterStep1Activity: BaseActivity<ActivityRegisterStep1Binding>(ActivityRegisterStep1Binding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nextBtn = binding.registerStep1BtnNext

        nextBtn.setOnClickListener {
            val intent = Intent(this, RegisterStep2Activity::class.java)
            startActivity(intent)
        }

        val back = binding.registerStep1ImgbtnBack

        back.setOnClickListener {
            finish()
        }
    }
}