package com.pipix.pipi.src.fragment.insertPerson

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentInsertBinding
import com.pipix.pipi.src.main.MainActivity

class InsertFragment : BaseFragment<FragmentInsertBinding>(FragmentInsertBinding::bind, R.layout.fragment_insert) {


    companion object {
        var dataList = mutableListOf<TestData>()
        var recyclerviewAdapter = InsertAdapter(dataList)

        // 방문 요일의 체크 상태를 처리할 변수
        var monChecked : Boolean = false
        var tuesChecked : Boolean = false
        var wedChecked : Boolean = false
        var thuChecked : Boolean = false
        var friChecked : Boolean = false
        var satChecked : Boolean = false
        var sunChecked : Boolean = false
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toggleBtnMon = binding.insertMon
        val toggleBtnTues = binding.insertTues
        val toggleBtnWed = binding.insertWed
        val toggleBtnThu = binding.insertThu
        val toggleBtnFri = binding.insertFri
        val toggleBtnSat = binding.insertSat
        val toggleBtnSun = binding.insertSun


        toggleBtnMon.setOnClickListener{
            if(toggleBtnMon.isChecked) {
                CustomDialog(context as MainActivity, "월요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnMon.isChecked = true }
        }
        toggleBtnTues.setOnClickListener{
            if(toggleBtnTues.isChecked) {
                CustomDialog(context as MainActivity, "화요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnTues.isChecked = true }
        }
        toggleBtnWed.setOnClickListener{
            if(toggleBtnWed.isChecked) {
                CustomDialog(context as MainActivity, "수요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnWed.isChecked = true }
        }
        toggleBtnThu.setOnClickListener{
            if(toggleBtnThu.isChecked) {
                CustomDialog(context as MainActivity, "목요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnThu.isChecked = true }
        }
        toggleBtnFri.setOnClickListener{
            if(toggleBtnFri.isChecked) {
                CustomDialog(context as MainActivity, "금요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnFri.isChecked = true }
        }
        toggleBtnSat.setOnClickListener{
            if(toggleBtnSat.isChecked) {
                CustomDialog(context as MainActivity, "토요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnSat.isChecked = true }
        }
        toggleBtnSun.setOnClickListener{
            if(toggleBtnSun.isChecked) {
                CustomDialog(context as MainActivity, "일요일").show() }
            else {
                showCustomToast("아이템 삭제 구현 예정입니다")
                toggleBtnSun.isChecked = true }
        }




        val recyclerView = binding.insertRecyclerview
        recyclerView.setLayoutManager(object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        })








        // 리사이클러뷰에 Adapter 객체 지정.
        recyclerView.adapter = recyclerviewAdapter
    }


}