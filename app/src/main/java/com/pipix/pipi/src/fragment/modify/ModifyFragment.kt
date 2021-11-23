package com.pipix.pipi.src.fragment.modify

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentModifyBinding
import com.pipix.pipi.src.fragment.insertPerson.CustomDialog
import com.pipix.pipi.src.fragment.insertPerson.InsertAdapter
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.dataList
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.friTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.friliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.monTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.monliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.recyclerviewAdapter
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.satTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.satliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.sunTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.sunliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.thuTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.thuliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.tuesTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.tuesliveChecked
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.wedTime
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment.Companion.wedliveChecked
import com.pipix.pipi.src.fragment.insertPerson.SetTime
import com.pipix.pipi.src.main.MainActivity


class ModifyFragment : BaseFragment<FragmentModifyBinding>(FragmentModifyBinding::bind, R.layout.fragment_modify) {



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

    val args: ModifyFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.modifyImgbtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        storage= FirebaseStorage.getInstance()

        val data =  args.myArg2

        viewBind()

        dataBind(data)

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
            if(name.text != null && age.text != null && genderType != null && address.text != null){

                showLoadingDialog(context as MainActivity)
                Handler(Looper.getMainLooper()).postDelayed({
                    dismissLoadingDialog()
                }, 5000)

                val imageUrl : String? = null


                if(selectImage!=null) {

                    val fileName = "${binding.modifyEdittextName.text}${binding.modifyEdittextAge.text}.jpg"

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
                            Log.d("insert테스트","이미지 URL 호출 성공")

                            val downloadUri = task.result
                            val oldData = Old(data.oldID, "userID", name.text.toString(), age.text.toString().toInt(), genderType!! ,address.text.toString(), downloadUri.toString(),
                                monTime,
                                tuesTime,
                                wedTime,
                                thuTime,
                                friTime,
                                satTime,
                                sunTime
                            )

                            MainActivity.viewModel.addOld(oldData)

                            //all clear
                            dataClear()

                        } else {
                            // Handle failures
                            Log.d("insert테스트","이미지 업로드 실패")
                        }
                    }
                }
                else{
                    MainActivity.viewModel.addOld(
                        Old(0, "userID", name.text.toString(), age.text.toString().toInt(), genderType!! ,address.text.toString(), imageUrl,
                            monTime,
                            tuesTime,
                            wedTime,
                            thuTime,
                            friTime,
                            satTime,
                            sunTime
                        ))
                    //all clear
                    dataClear()

                }
            }else { showCustomToast("필수 항목을 모두 입력하세요") }
        }

        val recyclerView = binding.modifyRecyclerview
        recyclerView.setLayoutManager(object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        })

        recyclerView.adapter = recyclerviewAdapter

        binding.modifyImgbtnChangeImage.setOnClickListener {
            var intent= Intent(Intent.ACTION_PICK) //선택하면 무언가를 띄움. 묵시적 호출
            intent.type="image/*"
            startActivityForResult(intent,IMAGE_PICK)
        }


    }

    fun viewBind() {
        complete = binding.modifyBtnComplate
        name = binding.modifyEdittextName
        address = binding.modifyEdittextAddress
        age = binding.modifyEdittextAge
        radioGroup = binding.modifyRadiogroup
        BtnMon = binding.modifyMon
        BtnTues = binding.modifyTues
        BtnWed = binding.modifyWed
        BtnThu = binding.modifyThu
        BtnFri = binding.modifyFri
        BtnSat = binding.modifySat
        BtnSun = binding.modifySun
    }

    fun dataBind(old: Old) {

        binding.modifyEdittextName.setText(old.oldName)
        binding.modifyEdittextAddress.setText(old.oldAddress)
        binding.modifyEdittextAge.setText(old.oldAge.toString())

        if (old.oldImage == null){
            binding.modifyCircleimageProfile.setImageResource(R.drawable.ic_basic_profile)}
        else{
            Glide.with(this)
                .load(old.oldImage.toString())
                .into(binding.modifyCircleimageProfile)}
        when(old.oldSex){
            1 -> binding.modifyMan.isChecked = true
            2 -> binding.modifyWoman.isChecked = true
        }


        if(old.mon != null){
            val setTime = old.mon.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"월요일"))
        }
        if(old.tue != null){
            val setTime = old.tue.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"화요일"))
        }
        if(old.wed != null){
            val setTime = old.wed.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"수요일"))
        }
        if(old.thu != null){
            val setTime = old.thu.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"목요일"))
        }
        if(old.fri != null){
            val setTime = old.fri.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"금요일"))
        }
        if(old.sat!= null){
            val setTime = old.sat.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"토요일"))
        }
        if(old.sun != null){
            val setTime = old.sun.split("-")
            val startTimeText = "${setTime[0]}:${setTime[1]}"
            val endTimeText = "${setTime[2]}:${setTime[3]}"
            dataList.add(SetTime(startTimeText,endTimeText,"일요일"))
        }



    }

    fun dataClear() {
        Log.d("modify","초기화")
        dataList.clear()
        recyclerviewAdapter.notifyDataSetChanged()
        Glide.with(this)
            .load(R.drawable.ic_basic_profile)
            .into(binding.modifyCircleimageProfile)
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
        monTime = null
        tuesTime = null
        wedTime = null
        thuTime = null
        friTime = null
        satTime = null
        sunTime = null
    }

    override fun onDetach() {
        super.onDetach()
        dataList.clear()
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==IMAGE_PICK&&resultCode== Activity.RESULT_OK){
            selectImage=data?.data
            binding.modifyCircleimageProfile.setImageURI(selectImage)
        }
    }

}



