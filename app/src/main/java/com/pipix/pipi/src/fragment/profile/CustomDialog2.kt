package com.pipix.pipi.src.fragment.profile

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.databinding.DialogDeleteitemBinding
import com.pipix.pipi.src.main.MainActivity

import java.util.*


class CustomDialog2(context: Context, val data : PureResult) : Dialog(context) {
    @RequiresApi(Build.VERSION_CODES.N)
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
            //삭제 코드
            MainActivity.viewModel.deletePureResult(data)
            dismiss()
        }



    }
}