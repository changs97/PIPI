package com.pipix.pipi.src.fragment.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.data.PureResult

class ProfileAdapter(private var dataList: MutableList<PureResult>) :  RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var delete: TextView? = null
        var dateTime: TextView? = null
        var itemPosition : Int? = null
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


            /* 삭제 코드 구현
              more!!.setOnClickListener {
                  val intent2 = Intent(view.context, DeleteItemActivity::class.java)
                  intent2.putExtra("delete",itemPosition)
                  view.getContext().startActivity(intent2)
              }
              */


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

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var currentItem = dataList[position]
        val time = currentItem.date
        viewHolder.dateTime?.setText(time.toString())
        viewHolder.data = dataList[position]
    }


    fun setData(user : MutableList<PureResult>){
        dataList = user
        notifyDataSetChanged()
    }

}

