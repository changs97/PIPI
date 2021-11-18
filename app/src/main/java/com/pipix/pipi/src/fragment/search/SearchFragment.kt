package com.pipix.pipi.src.fragment.search

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.data.PRViewModel
import com.pipix.pipi.databinding.FragmentSearchBinding
import com.pipix.pipi.src.fragment.insertPerson.InsertFragment
import com.pipix.pipi.src.fragment.insertPerson.TestData
import com.pipix.pipi.src.main.MainActivity
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    private var recyclerviewAdapter: RecyclerView.Adapter<SearchAdapter.ViewHolder>? =null
    private var searchList: MutableList<Old> = MainActivity.oldList as MutableList<Old>
    private lateinit var sViewModel: SearchViewModel
    private lateinit var updatedList: MutableList<Old>
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchText: EditText
    private lateinit var scope: CoroutineScope

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 임시 추가 데이터
        MainActivity.viewModel.addOld(Old(0, "userID", "KIM", 56,1,"목동", null,
        "12-30-16-00",null, null,null,null,null,null))
        MainActivity.viewModel.addOld(Old(0, "userID", "JU", 56,1,"목동", null,
            "12-30-16-00",null, null,null,null,null,null))


        searchText = binding.searchEdittextName
        searchText.addTextChangedListener {
            sViewModel.setSearchText( searchText.text.toString())
        }


        recyclerView = binding.searchRecyclerview
        recyclerView.layoutManager = object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return true
            }
        }

        //초기 기본 리스트 반환
        updatedList = searchList
        sViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        sViewModel.searchText.observe(viewLifecycleOwner,{

            updateList(it)
            recyclerviewAdapter = SearchAdapter(updatedList)

            // 리사이클러뷰에 Adapter 객체 지정.
            recyclerView.adapter = recyclerviewAdapter
        })
    }

    private fun updateList(str: String) {
        if(str.isNotEmpty()){
            updatedList = mutableListOf()
            for(old in searchList){
                if(old.oldName.contains(str)) updatedList.add(old)
            }
        }
    }
}