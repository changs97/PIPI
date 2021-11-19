package com.pipix.pipi.src.fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.data.Old
import com.pipix.pipi.src.main.MainActivity

class SearchAdapter(private val oldList: MutableList<Old>)  :  RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name : TextView? = null
        var image: ImageView? = null
        var address: TextView? = null
        var schedule: TextView? = null
        lateinit var old: Old

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.search_card_name)
            address = view.findViewById(R.id.search_card_address)
            image = view.findViewById(R.id.search_card_image)
            schedule = view.findViewById(R.id.search_card_schedule)

            // 객체 넘겨주기
            view.setOnClickListener {
                MainActivity.viewModel.currentOld = old
                findNavController(view).navigate(R.id.action_searchFragment_to_profileFragment)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_card_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = oldList[position]

        holder.name!!.text = currentItem.oldName + " " + currentItem.oldID
        holder.image!!.setImageResource(R.drawable.ic_basic_profile)
        holder.address!!.text = currentItem.oldAddress
        holder.schedule!!.text = getSchedule(currentItem)
        holder.old = oldList[position]
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    companion object{
        fun getSchedule(old: Old): String{
            var schedule = ""
            if(!old.mon.isNullOrBlank()){
                val sl = old.mon.split("-")
                schedule += "월요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.tue.isNullOrBlank()){
                val sl = old.tue.split("-")
                schedule += "화요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.wed.isNullOrBlank()){
                val sl = old.wed.split("-")
                schedule += "화요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.thu.isNullOrBlank()){
                val sl = old.thu.split("-")
                schedule += "목요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.fri.isNullOrBlank()){
                val sl = old.fri.split("-")
                schedule += "금요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.sat.isNullOrBlank()){
                val sl = old.sat.split("-")
                schedule += "토요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.sun.isNullOrBlank()){
                val sl = old.sun.split("-")
                schedule += "일요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            return schedule
        }
    }
}