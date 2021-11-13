package com.changs.pipi.src.fragment.plus

import android.os.Bundle
import android.view.View
import com.changs.pipi.R
import com.changs.pipi.config.BaseFragment
import com.changs.pipi.databinding.FragmentPlusBinding

class PlusFragment : BaseFragment<FragmentPlusBinding>(FragmentPlusBinding::bind,
    R.layout.fragment_plus
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}