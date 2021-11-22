package com.pipix.pipi.src.fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pipix.pipi.R
import com.pipix.pipi.data.Old
import com.pipix.pipi.src.main.MainActivity

class SearchAdapter(private val oldList: MutableList<Old>, private val which: Int)  :  RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val which: Int) : RecyclerView.ViewHolder(view) {

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
                val where = if(which == 1) R.id.action_searchFragment_to_profileFragment
                else R.id.action_homeFragment_to_profileFragment
                findNavController(view).navigate(where)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.search_card_layout, viewGroup, false)

        return ViewHolder(view, which)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = oldList[position]


        if (currentItem.oldImage == null){
            holder.image!!.setImageResource(R.drawable.ic_basic_profile)}
        else{
            Glide.with(holder.itemView.getContext())
                .load(currentItem.oldImage)
                .into(holder.image!!)
        }

        holder.name!!.text = currentItem.oldName
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
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.tue.split("-")
                schedule += "화요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.wed.isNullOrBlank()){
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.wed.split("-")
                schedule += "화요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.thu.isNullOrBlank()){
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.thu.split("-")
                schedule += "목요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.fri.isNullOrBlank()){
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.fri.split("-")
                schedule += "금요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.sat.isNullOrBlank()){
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.sat.split("-")
                schedule += "토요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            if(!old.sun.isNullOrBlank()){
                if(schedule.isNotEmpty()) schedule+="\n"
                val sl = old.sun.split("-")
                schedule += "일요일 ${sl[0]}:${sl[1]} - ${sl[2]}:${sl[3]} "
            }
            return schedule
        }
    }
}