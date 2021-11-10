package com.changs.pipi.src.logged_out

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.changs.pipi.R
import com.changs.pipi.config.BaseActivity
import com.changs.pipi.databinding.ActivityLoggedOutBinding
import com.changs.pipi.src.logged_out.join.register_step1.RegisterStep1Activity
import com.changs.pipi.src.logged_out.login.LoginActivity

class LoggedOutActivity : BaseActivity<ActivityLoggedOutBinding>(ActivityLoggedOutBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginBtn = binding.loggedOutBtnLogin
        val joinBtn = binding.loggedOutBtnJoin

        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        joinBtn.setOnClickListener {
            val intent = Intent(this, RegisterStep1Activity::class.java)
            startActivity(intent)
        }

    }
}