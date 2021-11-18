package com.pipix.pipi.src.fragment.insertPerson

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R


class InsertAdapter(val data : List<String>)  :  RecyclerView.Adapter<InsertAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView? = null
        var startTime : Button? = null
        var endTime  : Button? = null






        init {
            // Define click listener for the ViewHolder's View.

            title = view.findViewById(R.id.insert_item_title)
            startTime = view.findViewById(R.id.insert_item_btn_start)
            endTime = view.findViewById(R.id.insert_item_btn_end)


            startTime!!.setOnClickListener {

            }

            endTime!!.setOnClickListener {

            }

        }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.insert_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.title!!.text = data[position]



    }


}