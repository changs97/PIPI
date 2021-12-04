package com.pipix.pipi.src.fragment.logged_out.join.register_step2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Webservice
import com.pipix.pipi.databinding.FragmentRegisterStep2Binding
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterStep2Fragment : BaseFragment<FragmentRegisterStep2Binding>(
    FragmentRegisterStep2Binding::bind, R.layout.fragment_register_step2) {

    val args: RegisterStep2FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = binding.registerStep2EdittextName
        val back = binding.registerStep2ImgbtnBack

        val userId = args.resisterArg.userId
        val password = args.resisterArg.password

        back.setOnClickListener {
            findNavController().popBackStack()
        }
        val t = binding.registerStep2BtnJoin
        t.setOnClickListener {

            t.isEnabled = false

            tryPostSignUp(SignUpBody(userId,userName.text.toString(),password))

            t.isEnabled = true



        }
    }


    fun tryPostSignUp(body : SignUpBody){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.postSignUp(body).enqueue(object :
            Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {

                if(response.isSuccessful())
                {
                    // 응답을 잘 받은 경우
                    val data = response.body()
                    Log.d("tryPostSignUp",data.toString()+"성공")
                    findNavController().navigate(R.id.action_registerStep2Fragment_to_loginFragment)

                } else {
                    // 통신은 성공했지만 응답에 문제가 있는 경우
                    Log.d("tryPostSignUp","응답에 문제")
                }

            }


            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("TEST_tryPostSignUp",t.message ?:"통신 오류")
                //통신 실패
                //통신 결는 성공인데 이 함수가 호출되는 이유를 알아봐야함
                showCustomToast("회원가입 실패")

            }
        })
    }
}