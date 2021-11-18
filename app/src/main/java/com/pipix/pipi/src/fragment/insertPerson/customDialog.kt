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
import com.pipix.pipi.databinding.DialogCustomBinding

class CustomDialog(context: Context, val title : String) : Dialog(context) {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val dialogBinding = DialogCustomBinding
            .inflate(LayoutInflater.from(getContext()))
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(dialogBinding.root)

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
            dismiss()
        }

        setStartTime.setOnClickListener {
            getTime(it as Button, context)
        }

        setEndTime.setOnClickListener {
            getTime(it as Button, context)
        }

        complate.setOnClickListener {

            InsertFragment.dataList.add(TestData(setStartTime.text.toString(), setEndTime.text.toString(), title))
            InsertFragment.recyclerviewAdapter.notifyDataSetChanged()
            dismiss()
        }


    }

}