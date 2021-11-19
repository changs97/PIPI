package com.pipix.pipi.src.fragment.search

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
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
    private lateinit var updatedList: MutableList<Old>
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchText = binding.searchEdittextName

        //초기 기본 리스트 반환
        updatedList = searchList

        searchText.addTextChangedListener {
            updateList(searchText.text.toString())
            recyclerviewAdapter = SearchAdapter(updatedList)
            recyclerView.adapter = recyclerviewAdapter
        }



        recyclerView = binding.searchRecyclerview
        recyclerView.layoutManager = object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return true
            }
        }

        recyclerviewAdapter = SearchAdapter(updatedList)
        recyclerView.adapter = recyclerviewAdapter
    }

    private fun updateList(str: String) {
        if(str.isNotBlank()){
            updatedList = mutableListOf()
            for(old in searchList){
                if(old.oldName.contains(str)) updatedList.add(old)
            }
        }else updatedList = searchList
    }
}