package com.pipix.pipi.src.fragment.profile

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.src.main.MainActivity

class ProfileAdapter(private var dataList: MutableList<PureResult>) :  RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var delete: TextView? = null
        var dateTime: TextView? = null
        var type: TextView? = null
        lateinit var data : PureResult

        init {
            delete = view.findViewById(R.id.item_delete)
            dateTime = view.findViewById(R.id.item_time)
            type = view.findViewById(R.id.item_type)


            view.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToChartFragment(data)
                Navigation.findNavController(view).navigate(action)

            }


           //삭제 코드 구현
              delete!!.setOnClickListener {
                  //삭제 다이얼로그

                  //삭제 코드
                  MainActivity.viewModel.deletePureResult(data)
              }



        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.profile_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var currentItem = dataList[position]
        val sdf =  SimpleDateFormat("yyyy.MM.dd a hh시 mm분")
        val time = sdf.format(currentItem.date)
        viewHolder.dateTime?.setText(time.toString())
        viewHolder.data = dataList[position]
    }


    fun setData(user : MutableList<PureResult>){
        dataList = user
        notifyDataSetChanged()
    }

}

