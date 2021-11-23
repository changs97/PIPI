package com.pipix.pipi.src.fragment.modify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentModifyBinding
import com.pipix.pipi.src.fragment.chart.ChartFragmentArgs
import com.pipix.pipi.src.main.MainActivity


class ModifyFragment : BaseFragment<FragmentModifyBinding>(FragmentModifyBinding::bind, R.layout.fragment_modify) {

    val args: ModifyFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data =  args.myArg2

        dataBind(data)



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

        }
        if(old.tue != null){

        }
        if(old.wed != null){

        }
        if(old.thu != null){

        }
        if(old.fri != null){

        }
        if(old.sat!= null){

        }
        if(old.sun != null){

        }



    }



}