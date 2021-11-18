package com.pipix.pipi.src.fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.data.Old

class SearchAdapter(private val oldList: MutableList<Old>)  :  RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name : TextView? = null
        var image: ImageView? = null

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.search_card_name)
            image = view.findViewById(R.id.search_card_image)
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
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

}