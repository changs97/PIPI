package com.pipix.pipi.src.fragment.profile

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.data.Old
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.data.Webservice
import com.pipix.pipi.databinding.DialogDeleteitemBinding
import com.pipix.pipi.src.fragment.logged_out.join.register_step2.model.SignUpBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import com.pipix.pipi.src.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

import java.util.*


class CustomDialog2(context: Context, val data : PureResult) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(getWindow())?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val dialogBinding = DialogDeleteitemBinding
            .inflate(LayoutInflater.from(getContext()))
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setContentView(dialogBinding.root)
        setOnCancelListener {
            dismiss()
        }

        val back = dialogBinding.deleteitemImageviewBtnBack
        val delete = dialogBinding.deleteitemButtonCheck

        back.setOnClickListener{
            dismiss()
        }
        delete.setOnClickListener {
            tryDeleteTestResult(data.oldID, data.date.getTime())
            MainActivity.viewModel.deletePureResult(data)
            dismiss()
        }



    }

    fun tryDeleteTestResult(patientId : Int, date : Long){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.deleteTestResult(patientId, date).enqueue(object :
            Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {

                if(response.isSuccessful())
                {
                    // 응답을 잘 받은 경우
                    val data = response.body()
                    Log.d("tryDeleteTestResult",data.toString()+"성공")


                } else {
                    // 통신은 성공했지만 응답에 문제가 있는 경우
                    Log.d("tryDeleteTestResult","응답에 문제")
                }

            }


            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("tryDeleteTestResult",t.message ?:"통신 오류")

            }
        })
    }

}