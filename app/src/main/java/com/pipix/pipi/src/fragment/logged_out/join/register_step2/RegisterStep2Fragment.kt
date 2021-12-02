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
import com.pipix.pipi.src.fragment.chart.ChartFragmentArgs
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpBody
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpResponse
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

            tryPostSignUp(SignUpBody(userId,userName.text.toString(),password))

            var name = ""
            name = if(userName.text.isNullOrBlank()) "userName" else userName.text.toString()
            with (ApplicationClass.sSharedPreferences.edit()) {
                putString(getString(R.string.sharedUserNameKey), name)
                commit()
            }
            Log.d("TEST_tryPostSignUp",userId+userName.text.toString()+password)


            findNavController().navigate(R.id.action_registerStep2Fragment_to_second_graph)
            findNavController().graph.startDestination = R.id.second_graph

        }
    }


    fun tryPostSignUp(body : SignUpBody){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.postSignUp(body).enqueue(object :
            Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) { Log.d("TEST_tryPostSignUp",response.body().toString()+"OnResponse")

            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("TEST_tryPostSignUp",t.message ?:"통신 오류")
            }
        })
    }
}