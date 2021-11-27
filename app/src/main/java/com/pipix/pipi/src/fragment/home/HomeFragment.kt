package com.pipix.pipi.src.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.pipix.pipi.R
import com.pipix.pipi.config.ApplicationClass
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.Old
import com.pipix.pipi.databinding.FragmentHomeBinding
import com.pipix.pipi.src.fragment.search.SearchAdapter
import com.pipix.pipi.src.main.MainActivity
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private var recyclerviewAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>? =null
    private var recyclerviewAdapter2: RecyclerView.Adapter<SearchAdapter.ViewHolder>? =null
    private var homeList = MainActivity.oldList
    private lateinit var updatedList: MutableList<Old>
    private lateinit var updatedList2: MutableList<Old>
    private lateinit var recyclerView: ViewPager2
    private lateinit var recyclerView2: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = ApplicationClass.sSharedPreferences.getString(getString(R.string.sharedUserNameKey),"default")
        binding.homeUserNameBar.text = "$userName 님"
        binding.homePlan.text = "오늘 방문 예정인 어르신"
        binding.homePlan2.text = "내일 방문 예정인 어르신"
        recyclerView = binding.homeRecyclerview
        recyclerView2 = binding.homeRecyclerview2


        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)

        updatedList = mutableListOf()
        getHomeList(day, updatedList) // Mon~ Sun => 1~7
        recyclerviewAdapter = ViewPagerAdapter(updatedList)
        recyclerView.adapter = recyclerviewAdapter

        recyclerView.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        recyclerView.clipToPadding = false
        recyclerView.clipChildren = false
        recyclerView.offscreenPageLimit = 100

        binding.homeLogout.setOnClickListener {
            CustomDialog3(context as MainActivity).show()
        }

        val offsetBetweenPages = resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages).toFloat()
        recyclerView.setPageTransformer { page, position ->
            val myOffset = position * -(2 * offsetBetweenPages)
            if (position < -1) {
                page.translationX = -myOffset
            } else if (position <= 1) {
                // Paging 시 Y축 Animation 배경색을 약간 연하게 처리
                val scaleFactor = 0.8f.coerceAtLeast(1 - Math.abs(position))
                page.translationX = myOffset
                page.scaleY = scaleFactor
                page.alpha = scaleFactor
            } else {
                page.alpha = 0f
                page.translationX = myOffset
            }
        }

        if(updatedList.size == 0) {
            val layoutParams = binding.homeNotify.layoutParams as LinearLayout.LayoutParams
            layoutParams.setMargins(0,80,0,80)
            binding.homeNotify.layoutParams = layoutParams
            binding.homeNotify.text = "금일 방문 예정 내역이 없습니다"
        }else{
            binding.homeNotify.visibility = View.GONE
        }


        recyclerView2.layoutManager = object : LinearLayoutManager(activity){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        updatedList2 = mutableListOf()
        getHomeList(day%7+1, updatedList2)
        recyclerviewAdapter2 = SearchAdapter(updatedList2, 0)
        recyclerView2.adapter = recyclerviewAdapter2


        if(updatedList2.size == 0) {
            val layoutParams = binding.homeNotify2.layoutParams as LinearLayout.LayoutParams
            layoutParams.setMargins(0,80,0,80)
            binding.homeNotify2.layoutParams = layoutParams
            binding.homeNotify2.text = "내일 방문 예정 내역이 없습니다"
        }

        MainActivity.viewModel.readAllOld.observe(viewLifecycleOwner,{
            homeList = it
            updatedList = mutableListOf()
            getHomeList(day, updatedList) // Mon~ Sun => 1~7
            recyclerviewAdapter = ViewPagerAdapter(updatedList)
            recyclerView.adapter = recyclerviewAdapter

            updatedList2 = mutableListOf()
            getHomeList(day%7+1, updatedList2)
            recyclerviewAdapter2 = SearchAdapter(updatedList2, 0)
            recyclerView2.adapter = recyclerviewAdapter2

            if(updatedList.size == 0) {
                val layoutParams = binding.homeNotify.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0,80,0,80)
                binding.homeNotify.layoutParams = layoutParams
                binding.homeNotify.text = "금일 방문 예정 내역이 없습니다"
            }else{
                binding.homeNotify.visibility = View.GONE
            }

            if(updatedList2.size == 0) {
                val layoutParams = binding.homeNotify2.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(0,80,0,80)
                binding.homeNotify2.layoutParams = layoutParams
                binding.homeNotify2.text = "내일 방문 예정 내역이 없습니다"
            }
        })
    }

    private fun getHomeList(day: Int, list: MutableList<Old>){
        when (day) {
            Calendar.MONDAY -> {
                for(old in homeList) if(!old.mon.isNullOrBlank()) list.add(old)
                list.sortBy { it.mon }}
            Calendar.TUESDAY -> {for(old in homeList) if(!old.tue.isNullOrBlank()) list.add(old)
                list.sortBy { it.tue }}
            Calendar.WEDNESDAY ->{for(old in homeList) if(!old.wed.isNullOrBlank()) list.add(old)
                list.sortBy { it.wed }}
            Calendar.THURSDAY ->{for(old in homeList) if(!old.thu.isNullOrBlank()) list.add(old)
                list.sortBy { it.thu }}
            Calendar.FRIDAY ->{for(old in homeList) if(!old.fri.isNullOrBlank()) list.add(old)
                list.sortBy { it.fri }}
            Calendar.SATURDAY ->{for(old in homeList) if(!old.sat.isNullOrBlank()) list.add(old)
                list.sortBy { it.sat }}
            Calendar.SUNDAY -> {for(old in homeList) if(!old.sun.isNullOrBlank()) list.add(old)
                list.sortBy { it.sun }}
        }
    }
}