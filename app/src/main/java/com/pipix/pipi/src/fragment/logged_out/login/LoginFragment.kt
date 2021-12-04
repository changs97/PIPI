package com.pipix.pipi.src.fragment.logged_out.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.data.Webservice
import com.pipix.pipi.databinding.FragmentLoginBinding
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import com.pipix.pipi.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class LoginFragment  : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val back = binding.loginImgbtnBack
        val userID = binding.loginEdittextId
        val password = binding.loginEdittextPassword

        back.setOnClickListener {
            findNavController().popBackStack()
        }


        val t = binding.loginBtnLogin
        t.setOnClickListener {

            t.isEnabled = false

            tryGetLogin(LoginBody(userID.text.toString(), password.text.toString()))

            t.isEnabled = true

        }


    }

    fun tryGetLogin(body : LoginBody){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.postLogin(body).enqueue(object :
            Callback<LoginResponse> {
            @SuppressLint("SimpleDateFormat")
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("TEST_tryGetLogin",t.message ?:"통신 오류")
                showCustomToast("로그인 실패")
            }
        })
    }
}