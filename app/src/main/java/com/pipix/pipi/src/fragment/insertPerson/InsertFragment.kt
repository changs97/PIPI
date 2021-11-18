package com.pipix.pipi.src.fragment.insertPerson

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
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
        var monChecked = false
        var tuesChecked  = false
        var wedChecked  = false
        var thuChecked = false
        var friChecked = false
        var satChecked = false
        var sunChecked  = false

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
        var genderType : Int? = null

        val BtnMon = binding.insertMon
        val BtnTues = binding.insertTues
        val BtnWed = binding.insertWed
        val BtnThu = binding.insertThu
        val BtnFri = binding.insertFri
        val BtnSat = binding.insertSat
        val BtnSun = binding.insertSun

        val swipeLayout = binding.insertSwiperefreshlayot

        fun refresh() {
            swipeLayout.post { swipeLayout.isRefreshing = true
                BtnMon.isChecked = monChecked
                BtnTues.isChecked =  tuesChecked
                BtnWed.isChecked = wedChecked
                BtnThu.isChecked = thuChecked
                BtnFri.isChecked = friChecked
                BtnSat.isChecked = satChecked
                BtnSun.isChecked = sunChecked
                swipeLayout.isRefreshing = false
            }}




        swipeLayout.setOnRefreshListener {
            BtnMon.isChecked = monChecked
            BtnTues.isChecked =  tuesChecked
            BtnWed.isChecked = wedChecked
            BtnThu.isChecked = thuChecked
            BtnFri.isChecked = friChecked
            BtnSat.isChecked = satChecked
            BtnSun.isChecked = sunChecked
            swipeLayout.isRefreshing = false
        }







        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.insert_man-> genderType = 1
                R.id.insert_woman-> genderType = 2
            }
        }




        BtnMon.setOnClickListener{
            if(BtnMon.isChecked) {
                CustomDialog(context as MainActivity, "월요일").show()
                monChecked = true
            } else BtnMon.isChecked = true
            refresh()}
        BtnTues.setOnClickListener{
            if(BtnTues.isChecked){
                CustomDialog(context as MainActivity, "화요일").show()
                tuesChecked = true
            } else BtnTues.isChecked = true
            refresh()}
        BtnWed.setOnClickListener{
            if(BtnWed.isChecked){
                CustomDialog(context as MainActivity, "수요일").show()
                wedChecked = true
            } else BtnWed.isChecked = true
            refresh()}
        BtnThu.setOnClickListener{
            if(BtnThu.isChecked){
                CustomDialog(context as MainActivity, "목요일").show()
                thuChecked = true
            } else BtnThu.isChecked = true
            refresh()}
        BtnFri.setOnClickListener{
            if(BtnFri.isChecked){
                CustomDialog(context as MainActivity, "금요일").show()
                friChecked =true
            } else BtnFri.isChecked = true
            refresh()}
        BtnSat.setOnClickListener{
            if(BtnSat.isChecked){
                CustomDialog(context as MainActivity, "토요일").show()
                satChecked = true
            } else BtnSat.isChecked = true
            refresh()}
        BtnSun.setOnClickListener{
            if(BtnSun.isChecked){
                CustomDialog(context as MainActivity, "일요일").show()
                sunChecked = true
            } else BtnSun.isChecked = true
            refresh()}

        complete.setOnClickListener {
            if(name.text != null && age.text != null && genderType != null && address.text != null){
                MainActivity.viewModel.addOld(
                    Old(0, "userID", name.text.toString(), age.text.toString().toInt(), genderType!! ,address.text.toString(), null,
                        monTime, tuesTime, wedTime, thuTime, friTime, satTime, sunTime))

                dataList.clear()
                recyclerviewAdapter.notifyDataSetChanged()
                name.text = null
                age.text = null
                address.text = null
                genderType = null
                monChecked = false
                tuesChecked  = false
                wedChecked  = false
                thuChecked = false
                friChecked = false
                satChecked = false
                sunChecked  = false
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