package com.pipix.pipi.src.fragment.insertPerson


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R





class InsertAdapter(val dataList: MutableList<SetTime> )  :  RecyclerView.Adapter<InsertAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView? = null
        var startTime : TextView? = null
        var endTime  : TextView? = null
        var deleteBtn : ImageButton? = null


        init {
            title = view.findViewById(R.id.insert_item_title)
            startTime = view.findViewById(R.id.insert_item_text_start)
            endTime = view.findViewById(R.id.insert_item_text_end)
            deleteBtn = view.findViewById(R.id.insert_item_imgbtn_delete)
        }
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.insert_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val currentItem = dataList[position]


        viewHolder.title!!.text = currentItem.title
        viewHolder.startTime!!.text = currentItem.startTime
        viewHolder.endTime!!.text = currentItem.endTime

        viewHolder.deleteBtn!!.setOnClickListener {
            removeItem(position)
            when(currentItem.title) {
                "월요일" -> {
                InsertFragment.monTime = null
                    InsertFragment.monliveChecked.value = false }
                "화요일" -> {
                    InsertFragment.tuesTime = null
                    InsertFragment.tuesliveChecked.value = false}
                "수요일" -> {
                    InsertFragment.wedTime = null
                    InsertFragment.wedliveChecked.value = false}
                "목요일" -> {
                    InsertFragment.thuTime = null
                    InsertFragment.thuliveChecked.value = false}
                "금요일" -> {
                    InsertFragment.friTime = null
                    InsertFragment.friliveChecked.value = false}
                "토요일" -> {
                    InsertFragment.satTime = null
                    InsertFragment.satliveChecked.value = false}
                "일요일" -> {
                    InsertFragment.sunTime = null
                    InsertFragment.sunliveChecked.value = false}
            }

        }


    }

}