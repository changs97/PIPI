package com.pipix.pipi.src.fragment.insertPerson

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import okhttp3.internal.http2.Http2Connection
import java.security.AccessControlContext




class InsertAdapter(val dataList: MutableList<TestData> )  :  RecyclerView.Adapter<InsertAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView? = null
        var startTime : TextView? = null
        var endTime  : TextView? = null
        var deleteBtn : ImageButton? = null



        init {

            // Define click listener for the ViewHolder's View.

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

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.insert_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        var currentItem = dataList[position]


        viewHolder.title!!.text = currentItem.title
        viewHolder.startTime!!.text = currentItem.startTime
        viewHolder.endTime!!.text = currentItem.endTime

        viewHolder.deleteBtn!!.setOnClickListener {
            removeItem(position)
            when(currentItem.title) {
                "월요일" -> {InsertFragment.monChecked = false
                InsertFragment.monTime = null}
                "화요일" -> {InsertFragment.tuesChecked = false
                    InsertFragment.tuesTime = null}
                "수요일" -> {InsertFragment.wedChecked = false
                    InsertFragment.wedTime = null}
                "목요일" -> {InsertFragment.thuChecked = false
                    InsertFragment.thuTime = null}
                "금요일" -> {InsertFragment.friChecked = false
                    InsertFragment.friTime = null}
                "토요일" -> {InsertFragment.satChecked = false
                    InsertFragment.satTime = null}
                "일요일" -> {InsertFragment.sunChecked = false
                    InsertFragment.sunTime = null}
            }

        }




    }


}