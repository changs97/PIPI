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
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.data.Webservice
import com.pipix.pipi.src.fragment.insertPerson.model.InsertBody
import com.pipix.pipi.src.fragment.insertPerson.model.InsertResponse
import com.pipix.pipi.src.fragment.insertPerson.model.InsertScheduleBody
import com.pipix.pipi.src.fragment.insertPerson.model.InsertScheduleResponse
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginBody
import com.pipix.pipi.src.fragment.logged_out.login.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InsertFragment : BaseFragment<FragmentInsertBinding>(FragmentInsertBinding::bind, R.layout.fragment_insert) {

    companion object {
        var dataList = mutableListOf<SetTime>()
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


    val IMAGE_PICK = 1111
    var selectImage: Uri?=null
    lateinit var storage: FirebaseStorage
    var genderType : Int? = null
    lateinit var complete : Button
    lateinit var name : EditText
    lateinit var address : EditText
    lateinit var age : EditText
    lateinit var radioGroup : RadioGroup
    lateinit var BtnMon : ToggleButton
    lateinit var BtnTues : ToggleButton
    lateinit var BtnWed : ToggleButton
    lateinit var BtnThu : ToggleButton
    lateinit var BtnFri : ToggleButton
    lateinit var BtnSat : ToggleButton
    lateinit var BtnSun : ToggleButton

    val userId = ApplicationClass.prefs.userId

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        storage= FirebaseStorage.getInstance()

        viewBind()


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
                R.id.insert_man -> genderType = 1
                R.id.insert_woman -> genderType = 2
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
            complete.isEnabled = false
            showLoadingDialog(context as MainActivity)
            if(name.text != null && age.text != null && genderType != null && address.text != null){


                if(selectImage!=null) {

                    val fileName = "${binding.insertEdittextName.text}${binding.insertEdittextAge.text}.jpg"

                    val ref = storage.getReference().child("image").child(fileName)
                    val uploadTask = ref.putFile(selectImage!!)

                    val urlTask = uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                            }
                        }
                        ref.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val downloadUri = task.result

                            tryPostInsert(InsertBody(address.text.toString(),age.text.toString(),downloadUri.toString(),name.text.toString(),genderType!!))

                            Glide.with(this)
                                .load(R.drawable.ic_basic_profile).centerCrop()
                                .into(binding.insertCircleimageProfile)

                            dismissLoadingDialog()

                        } else {
                            // Handle failures
                            Log.d("insert테스트","이미지 업로드 실패")
                        }
                    }

                }
                else{
                    tryPostInsert(InsertBody(address.text.toString(),age.text.toString(),null,name.text.toString(),genderType!!))

                    Glide.with(this)
                        .load(R.drawable.ic_basic_profile).centerCrop()
                        .into(binding.insertCircleimageProfile)
                    dismissLoadingDialog()
                }
            }else { showCustomToast("필수 항목을 모두 입력하세요") }
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
        complete.isEnabled = true
    }


    fun tryPostInsert(body : InsertBody){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.postInsert(body, userId!!.toInt()).enqueue(object :
            Callback<InsertResponse> {
            override fun onResponse(
                call: Call<InsertResponse>,
                response: Response<InsertResponse>
            ) { Log.d("tryPostInsert",response.body().toString())
                val data = response.body() as InsertResponse


                MainActivity.viewModel.addOld(
                    //userId 바꿔서 넣어주기
                    Old( data.id, data.caregiverId.toString(), data.name, data.age, body.sex ,data.address, data.imageURL,
                        monTime, tuesTime, wedTime, thuTime, friTime, satTime, sunTime))

                tryPutInsert(InsertScheduleBody(friTime, monTime, satTime, sunTime, thuTime,
                    tuesTime, wedTime), data.id)


            }

            override fun onFailure(call: Call<InsertResponse>, t: Throwable) {
                Log.d("tryPostInsert",t.message ?:"통신 오류")
            }
        })
    }

   
    fun tryPutInsert(body : InsertScheduleBody, patientId : Int){
        val UploadRetrofitInterface = ApplicationClass.sRetrofit.create(Webservice::class.java)
        UploadRetrofitInterface.putSchedule(body, patientId).enqueue(object :
            Callback<InsertScheduleResponse> {
            override fun onResponse(
                call: Call<InsertScheduleResponse>,
                response: Response<InsertScheduleResponse>
            ) { Log.d("tryPostInsert",response.body().toString())
                val data = response.body() as InsertScheduleResponse
                //all clear
                dataClear()

            }

            override fun onFailure(call: Call<InsertScheduleResponse>, t: Throwable) {
                Log.d("tryPostInsert",t.message ?:"통신 오류")
            }
        })
    }



    fun viewBind() {
        complete = binding.insertBtnComplate
        name = binding.insertEdittextName
        address = binding.insertEdittextAddress
        age = binding.insertEdittextAge
        radioGroup = binding.insertRadiogroup
        BtnMon = binding.insertMon
        BtnTues = binding.insertTues
        BtnWed = binding.insertWed
        BtnThu = binding.insertThu
        BtnFri = binding.insertFri
        BtnSat = binding.insertSat
        BtnSun = binding.insertSun
    }

    fun dataClear() {
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


    override fun onDetach() {
        super.onDetach()
        dataClear()
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_PICK&&resultCode== Activity.RESULT_OK){
            selectImage=data?.data
            binding.insertCircleimageProfile.setImageURI(selectImage)
        }
    }

}