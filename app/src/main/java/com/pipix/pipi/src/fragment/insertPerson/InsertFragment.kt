package com.pipix.pipi.src.fragment.insertPerson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentInsertBinding
import com.pipix.pipi.src.main.MainActivity



class InsertFragment : BaseFragment<FragmentInsertBinding>(FragmentInsertBinding::bind, R.layout.fragment_insert) {


    companion object {
        var dataList = mutableListOf<TestData>()
        var recyclerviewAdapter = InsertAdapter(dataList)

        // 방문 요일의 체크 상태를 처리할 변수
        var monTime : String? = null
        var tuesTime : String? = null
        var wedTime : String? = null
        var thuTime : String? = null
        var friTime : String? = null
        var satTime : String? = null
        var sunTime : String? = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //기본 방문자 정보
        val complete = binding.insertBtnComplate
        val name = binding.insertEdittextName
        val address = binding.insertEdittextAddress
        val age = binding.insertEdittextAge
        val radioGroup = binding.insertRadiogroup
        var sexType : Int? = null

        val BtnMon = binding.insertMon
        val BtnTues = binding.insertTues
        val BtnWed = binding.insertWed
        val BtnThu = binding.insertThu
        val BtnFri = binding.insertFri
        val BtnSat = binding.insertSat
        val BtnSun = binding.insertSun

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.insert_man-> sexType = 1
                R.id.insert_woman-> sexType = 2
            }
        }

        BtnMon.setOnClickListener{ CustomDialog(context as MainActivity, "월요일").show()}
        BtnTues.setOnClickListener{ CustomDialog(context as MainActivity, "화요일").show()}
        BtnWed.setOnClickListener{ CustomDialog(context as MainActivity, "수요일").show()}
        BtnThu.setOnClickListener{ CustomDialog(context as MainActivity, "목요일").show()}
        BtnFri.setOnClickListener{ CustomDialog(context as MainActivity, "금요일").show()}
        BtnSat.setOnClickListener{ CustomDialog(context as MainActivity, "토요일").show()}
        BtnSun.setOnClickListener{ CustomDialog(context as MainActivity, "일요일").show()}

        complete.setOnClickListener {
            if(name.text != null && age.text != null && sexType != null && address.text != null){
                MainActivity.viewModel.addOld(
                    Old(0, "userID", name.text.toString(), age.text.toString().toInt(), sexType!! ,address.text.toString(), null,
                        monTime, tuesTime, wedTime, thuTime, friTime, satTime, sunTime))
                showCustomToast(MainActivity.viewModel.readAllOld.toString())
                dataList.clear()
                recyclerviewAdapter.notifyDataSetChanged()
                name.text = null
                age.text = null
                address.text = null
                sexType = null
                radioGroup.clearCheck()

            }
            else{ showCustomToast("필수 항목을 모두 입력하세요")
            }
        }




        val recyclerView = binding.insertRecyclerview
        recyclerView.setLayoutManager(object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        })

        recyclerView.adapter = recyclerviewAdapter
    }

    override fun onDetach() {
        super.onDetach()
        dataList.clear()
    }



}