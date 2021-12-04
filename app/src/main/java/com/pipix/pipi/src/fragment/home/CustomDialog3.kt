package com.pipix.pipi.src.fragment.home

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.databinding.DialogLogoutBinding
import com.pipix.pipi.src.main.MainActivity

import java.util.*


class CustomDialog3(context: Context, val view : HomeFragment) : Dialog(context) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(getWindow())?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val dialogBinding = DialogLogoutBinding
            .inflate(LayoutInflater.from(getContext()))
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setContentView(dialogBinding.root)
        setOnCancelListener {
            dismiss()
        }

        val back = dialogBinding.logoutImageviewBtnBack
        val delete = dialogBinding.logoutButtonCheck

        back.setOnClickListener{
            dismiss()
        }
        delete.setOnClickListener {
            //룸 데이터베이스 초기화 시켜야 함
            ApplicationClass.prefs.userName = null
            ApplicationClass.prefs.userId = null
            MainActivity.viewModel.deleteAllPureResult()
            MainActivity.viewModel.deleteAllOld()

            view.findNavController().navigate(R.id.action_global_start)


            dismiss()
        }



    }
}