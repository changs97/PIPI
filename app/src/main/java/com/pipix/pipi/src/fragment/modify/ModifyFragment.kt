package com.pipix.pipi.src.fragment.modify

import android.os.Bundle
import android.view.View
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentInsertBinding

class ModifyFragment : BaseFragment<FragmentInsertBinding>(FragmentInsertBinding::bind, R.layout.fragment_insert) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var title =binding.insertTextTitle
        title.setText(R.string.modify_title)


    }
}