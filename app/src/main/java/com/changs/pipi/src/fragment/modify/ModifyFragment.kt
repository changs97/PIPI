package com.changs.pipi.src.fragment.modify

import android.os.Bundle
import android.view.View
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentModifyBinding

class ModifyFragment : BaseFragment<FragmentModifyBinding>(
    FragmentModifyBinding::bind,
    R.layout.fragment_modify
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}