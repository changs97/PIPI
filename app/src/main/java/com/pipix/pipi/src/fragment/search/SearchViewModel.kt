package com.pipix.pipi.src.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    val searchText: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun setSearchText(str: String){
        searchText.postValue(str)
    }
}