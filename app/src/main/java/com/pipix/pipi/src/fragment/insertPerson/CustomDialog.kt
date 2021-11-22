package com.pipix.pipi.src.fragment.insertPerson

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.annotation.RequiresApi
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.DialogCustomBinding
import com.pipix.pipi.src.main.MainActivity
import android.content.DialogInterface

import android.R.attr.button
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import java.util.*


class CustomDialog(context: Context, val title : String) : Dialog(context) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(getWindow())?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


       val dialogBinding = DialogCustomBinding
            .inflate(LayoutInflater.from(getContext()))
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        setContentView(dialogBinding.root)
        setOnCancelListener {
            when(title) {
                "월요일" -> {InsertFragment.monliveChecked.value = false}
                "화요일" -> {InsertFragment.tuesliveChecked.value = false}
                "수요일" -> {InsertFragment.wedliveChecked.value = false}
                "목요일" -> {InsertFragment.thuliveChecked.value = false}
                "금요일" -> {InsertFragment.friliveChecked.value = false}
                "토요일" -> {InsertFragment.satliveChecked.value = false}
                "일요일" -> {InsertFragment.sunliveChecked.value = false}
            }
        }


        fun getTime(button: Button, context: Context){

            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                button.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

        val back = dialogBinding.dialogImgbtnBack
        val setStartTime = dialogBinding.dialogItemBtnStart
        val setEndTime = dialogBinding.dialogItemBtnEnd
        val complate = dialogBinding.dialogBtnComplete

        back.setOnClickListener {
            when(title) {
                "월요일" -> {InsertFragment.monliveChecked.value = false}
                "화요일" -> {InsertFragment.tuesliveChecked.value = false}
                "수요일" -> {InsertFragment.wedliveChecked.value = false}
                "목요일" -> {InsertFragment.thuliveChecked.value = false}
                "금요일" -> {InsertFragment.friliveChecked.value = false}
                "토요일" -> {InsertFragment.satliveChecked.value = false}
                "일요일" -> {InsertFragment.sunliveChecked.value = false}
            }
            dismiss()
        }

        setStartTime.setOnClickListener {
            getTime(it as Button, context)
        }

        setEndTime.setOnClickListener {
            getTime(it as Button, context)
        }

        complate.setOnClickListener {
            if(setStartTime.text.length == 5 && setEndTime.text.length == 5 ) {
                InsertFragment.dataList.add(
                    TestData(
                        setStartTime.text.toString(),
                        setEndTime.text.toString(),
                        title
                    ))

                var startTimeText = setStartTime.text.split(":")
                var endTimeText = setEndTime.text.split(":")
                when(title) {
                    "월요일" -> {InsertFragment.monTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "화요일" -> {InsertFragment.tuesTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "수요일" -> {InsertFragment.wedTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "목요일" -> {InsertFragment.thuTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "금요일" -> {InsertFragment.friTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "토요일" -> {InsertFragment.satTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                    "일요일" -> {InsertFragment.sunTime = "${startTimeText[0]}-${startTimeText[1]}-${endTimeText[0]}-${endTimeText[1]}"}
                }
                InsertFragment.recyclerviewAdapter.notifyDataSetChanged()
                dismiss()
            }
        }



    }


}