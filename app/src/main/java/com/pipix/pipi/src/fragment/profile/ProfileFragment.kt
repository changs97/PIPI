package com.pipix.pipi.src.fragment.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.databinding.FragmentProfileBinding
import com.pipix.pipi.src.fragment.modify.ModifyOld
import com.pipix.pipi.src.main.MainActivity

class ProfileFragment  : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile) {

    lateinit var old: Old

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        MainActivity.viewModel.readAllOld.observe(viewLifecycleOwner, {
            for(o in it) if(o.oldID == MainActivity.viewModel.currentOldID) {
                old = o
                dataBind(old)
            }
        })

        //dataBind(old)

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        val recyclerView = binding.profileRecyclerview
        recyclerView.setLayoutManager(object : LinearLayoutManager(context){
            override fun canScrollVertically(): Boolean {
                return true
            }
        })

        binding.profileImgbtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.profileBtnTest.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_pureFragment2)
        }
        binding.profileTextModify.setOnClickListener {
            val modifyOld = ModifyOld(old.oldID,old.userID,old.oldName,old.oldAge,old.oldSex,old.oldAddress,old.oldImage,old.mon
            ,old.tue,old.wed,old.thu,old.fri,old.sat,old.sun)
            val action = ProfileFragmentDirections.actionProfileFragmentToModifyFragment(myArg2 = modifyOld )
            Navigation.findNavController(view).navigate(action)
        }


        var resultDataList = mutableListOf<PureResult>()
        for(i in MainActivity.pureList){
            if(i.oldID == old.oldID){
                resultDataList.add(i)
            }
        }
        var recyclerviewAdapter = ProfileAdapter(resultDataList)
        recyclerView.adapter = recyclerviewAdapter


        // UserViewModel
        MainActivity.viewModel.readAllPureData.observe(viewLifecycleOwner, Observer { user ->
             resultDataList = mutableListOf()
            for(i in MainActivity.pureList){
                if(i.oldID == old.oldID){
                    resultDataList.add(i)
                }
            }
            val recyclerviewAdapter = ProfileAdapter(resultDataList)
            recyclerView.adapter = recyclerviewAdapter
        })
    }


    fun dataBind(old: Old) {
        binding.profileTextName.text = "${old.oldName}(${old.oldAge})"
        binding.profileTextAddress.text = old.oldAddress


        if (old.oldImage == null){
            binding.profileCircleimageProfile.setImageResource(R.drawable.ic_basic_profile)}
        else{
            Glide.with(this)
                .load(old.oldImage.toString())
                .into(binding.profileCircleimageProfile)}

        if(old.mon != null) {
            val mon = old.mon.split("-")
            Log.d("테스트",mon.toString())
            binding.profileTextDateMon.text = "월요일 ${mon[0]}:${mon[1]} - ${mon[2]}:${mon[3]}"
            binding.profileTextDateMon.visibility = VISIBLE}
        if(old.tue != null) {
            val tue = old.tue.split("-")
            binding.profileTextDateTue.text = "화요일 ${tue[0]}:${tue[1]} - ${tue[2]}:${tue[3]}"
            binding.profileTextDateTue.visibility = VISIBLE}
        if(old.wed != null) {
            val wed = old.wed.split("-")
            binding.profileTextDateWed.text = "수요일 ${wed[0]}:${wed[1]} - ${wed[2]}:${wed[3]}"
            binding.profileTextDateWed.visibility = VISIBLE}
        if(old.thu != null) {
            val thu = old.thu.split("-")
            binding.profileTextDateThu.text = "목요일 ${thu[0]}:${thu[1]} - ${thu[2]}:${thu[3]}"
            binding.profileTextDateThu.visibility = VISIBLE}
        if(old.fri != null) {
            val fri = old.fri.split("-")
            binding.profileTextDateFri.text = "금요일 ${fri[0]}:${fri[1]} - ${fri[2]}:${fri[3]}"
            binding.profileTextDateFri.visibility = VISIBLE}

        if(old.sat != null) {
            val sat= old.sat.split("-")
            binding.profileTextDateSat.text = "토요일 ${sat[0]}:${sat[1]} - ${sat[2]}:${sat[3]}"
            binding.profileTextDateSat.visibility = VISIBLE}
        if(old.sun != null) {
            val sun = old.sun.split("-")
            binding.profileTextDateSun.text = "일요일 ${sun[0]}:${sun[1]} - ${sun[2]}:${sun[3]}"
            binding.profileTextDateSun.visibility = VISIBLE}
    }

}