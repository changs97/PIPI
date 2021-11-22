package com.pipix.pipi.src.fragment.insertPerson

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentInsertBinding
import com.pipix.pipi.src.main.MainActivity
import java.util.*
import android.net.Uri
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat



class InsertFragment : BaseFragment<FragmentInsertBinding>(FragmentInsertBinding::bind, R.layout.fragment_insert) {

    companion object {
        var dataList = mutableListOf<TestData>()
        var recyclerviewAdapter = InsertAdapter(dataList)

        var monliveChecked : MutableLiveData<Boolean> = MutableLiveData()
        var tuesliveChecked  : MutableLiveData<Boolean> = MutableLiveData()
        var wedliveChecked  : MutableLiveData<Boolean> = MutableLiveData()
        var thuliveChecked : MutableLiveData<Boolean> = MutableLiveData()
        var friliveChecked : MutableLiveData<Boolean> = MutableLiveData()
        var satliveChecked : MutableLiveData<Boolean> = MutableLiveData()
        var sunliveChecked  : MutableLiveData<Boolean> = MutableLiveData()

        var monTime : String? = null
        var tuesTime : String? = null
        var wedTime : String? = null
        var thuTime : String? = null
        var friTime : String? = null
        var satTime : String? = null
        var sunTime : String? = null
    }


    val IMAGE_PICK=1111

    var selectImage: Uri?=null

    lateinit var storage: FirebaseStorage


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storage= FirebaseStorage.getInstance()


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






        monliveChecked.observe(viewLifecycleOwner, Observer {
            BtnMon.isChecked  = it
        })
        tuesliveChecked.observe(viewLifecycleOwner, Observer {
            BtnTues.isChecked  = it
        })
        wedliveChecked.observe(viewLifecycleOwner, Observer {
            BtnWed.isChecked  = it
        })
        thuliveChecked.observe(viewLifecycleOwner, Observer {
            BtnThu.isChecked  = it
        })
        friliveChecked.observe(viewLifecycleOwner, Observer {
            BtnFri.isChecked  = it
        })
        satliveChecked.observe(viewLifecycleOwner, Observer {
            BtnSat.isChecked  = it
        })
        sunliveChecked.observe(viewLifecycleOwner, Observer {
            BtnSun.isChecked  = it
        })



        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.insert_man-> genderType = 1
                R.id.insert_woman-> genderType = 2
            }
        }


        BtnMon.setOnClickListener{
            if(BtnMon.isChecked) {
                CustomDialog(context as MainActivity, "월요일").show()
            } else BtnMon.isChecked = true }
        BtnTues.setOnClickListener{
            if(BtnTues.isChecked){
                CustomDialog(context as MainActivity, "화요일").show()
            } else BtnTues.isChecked = true }
        BtnWed.setOnClickListener{
            if(BtnWed.isChecked){
                CustomDialog(context as MainActivity, "수요일").show()

            } else BtnWed.isChecked = true }
        BtnThu.setOnClickListener{
            if(BtnThu.isChecked){
                CustomDialog(context as MainActivity, "목요일").show()
            } else BtnThu.isChecked = true
        }
        BtnFri.setOnClickListener{
            if(BtnFri.isChecked){
                CustomDialog(context as MainActivity, "금요일").show()
            } else BtnFri.isChecked = true
          }
        BtnSat.setOnClickListener{
            if(BtnSat.isChecked){
                CustomDialog(context as MainActivity, "토요일").show()
            } else BtnSat.isChecked = true
        }
        BtnSun.setOnClickListener{
            if(BtnSun.isChecked){
                CustomDialog(context as MainActivity, "일요일").show()
            } else BtnSun.isChecked = true
        }

        complete.setOnClickListener {


            if(selectImage!=null) {

                showCustomToast("hihi")
                var fileName =
                    SimpleDateFormat("yyyyMMddHHmmss").format(Date()) // 파일명이 겹치면 안되기 떄문에 시년월일분초 지정
                storage.getReference().child("image").child(fileName)
                    .putFile(selectImage!!)//어디에 업로드할지 지정
            }


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
                radioGroup.clearCheck()
                monliveChecked.value = false
                tuesliveChecked.value = false
                wedliveChecked.value = false
                thuliveChecked.value = false
                friliveChecked.value = false
                satliveChecked.value = false
                sunliveChecked.value = false
                monTime  = null
                tuesTime  = null
                wedTime  = null
                thuTime  = null
                friTime  = null
                satTime  = null
                sunTime  = null
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







        binding.profileImgbtnChangeImage.setOnClickListener {
            var intent= Intent(Intent.ACTION_PICK) //선택하면 무언가를 띄움. 묵시적 호출
            intent.type="image/*"
            startActivityForResult(intent,IMAGE_PICK)
        }

    }






    override fun onDetach() {
        super.onDetach()
        dataList.clear()
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_PICK&&resultCode== Activity.RESULT_OK){
            selectImage=data?.data
            binding.insertCircleimageProfile.setImageURI(selectImage)
        }
    }
}