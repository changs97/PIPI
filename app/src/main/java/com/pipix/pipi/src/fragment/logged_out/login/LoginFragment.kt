package com.pipix.pipi.src.fragment.logged_out.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Webservice
import com.pipix.pipi.databinding.FragmentLoginBinding
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.isSuccessful()) {
                    // 응답을 잘 받은 경우
                    Log.d("TEST_tryGetLogin", response.body().toString())
                    val data = response.body() as LoginResponse
                    /*환자 테이블을 반환 받습니다.
                      여기서 로그아웃 또는 앱을 지우고 새로 로그인할 때 서버에서 저장된 데이터를 룸에 저장*/

                    ApplicationClass.prefs.userId = data.id.toString()
                    ApplicationClass.prefs.userName = data.name

                    findNavController().navigate(R.id.action_loginFragment_to_second_graph)
                    findNavController().graph.startDestination = R.id.second_graph
                }else{
                    //응답 실패 시 코드
                }


            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("TEST_tryGetLogin",t.message ?:"통신 오류")
                showCustomToast("로그인 실패")
            }
        })
    }
}