package com.pipix.pipi.src.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentHomeBinding
import com.pipix.pipi.src.fragment.search.SearchAdapter
import com.pipix.pipi.src.main.MainActivity
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private var recyclerviewAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>? =null
    private var recyclerviewAdapter2: RecyclerView.Adapter<SearchAdapter.ViewHolder>? =null
    private var homeList: MutableList<Old> = MainActivity.oldList as MutableList<Old>
    private lateinit var updatedList: MutableList<Old>
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = "userName"
        binding.homeTitle.text = "$userName 요양사님,"
        binding.homePlan.text = "오늘 방문 예정인 어르신"
        binding.homePlan2.text = "내일 방문 예정인 어르신"
        recyclerView = binding.homeRecyclerview
        recyclerView2 = binding.homeRecyclerview2

        recyclerView.layoutManager = object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        recyclerView2.layoutManager = object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        getHomeList(day) // Mon~ Sun => 1~7
        recyclerviewAdapter = HomeAdapter(updatedList)
        recyclerView.adapter = recyclerviewAdapter

        if(updatedList.size == 0) {
            val layoutParams = binding.homeNotify.layoutParams as LinearLayout.LayoutParams
            layoutParams.setMargins(0,80,0,80)
            binding.homeNotify.layoutParams = layoutParams
            binding.homeNotify.text = "금일 방문 예정 내역이 없습니다\n귀하의 노고에 감사드립니다"
        }

        // Tomorrow or Yesterday
        getHomeList((day+1)%7)
        recyclerviewAdapter2 = SearchAdapter(updatedList, 0)
        recyclerView2.adapter = recyclerviewAdapter2

        if(updatedList.size == 0) {
            val layoutParams = binding.homeNotify2.layoutParams as LinearLayout.LayoutParams
            layoutParams.setMargins(0,80,0,80)
            binding.homeNotify2.layoutParams = layoutParams
            binding.homeNotify2.text = "내일 방문 예정 내역이 없습니다"
        }

    }
    private fun getHomeList(day: Int){
        updatedList = mutableListOf()
        when (day) {
            Calendar.MONDAY -> {for(old in homeList) if(!old.mon.isNullOrBlank()) updatedList.add(old)}
            Calendar.TUESDAY -> {for(old in homeList) if(!old.thu.isNullOrBlank()) updatedList.add(old)}
            Calendar.WEDNESDAY ->{for(old in homeList) if(!old.wed.isNullOrBlank()) updatedList.add(old)}
            Calendar.THURSDAY ->{for(old in homeList) if(!old.thu.isNullOrBlank()) updatedList.add(old)}
            Calendar.FRIDAY ->{for(old in homeList) if(!old.fri.isNullOrBlank()) updatedList.add(old)}
            Calendar.SATURDAY ->{for(old in homeList) if(!old.sat.isNullOrBlank()) updatedList.add(old)}
            Calendar.SUNDAY -> {for(old in homeList) if(!old.sun.isNullOrBlank()) updatedList.add(old)}
        }
    }
}