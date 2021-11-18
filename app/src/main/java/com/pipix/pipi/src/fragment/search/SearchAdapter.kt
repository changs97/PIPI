package com.pipix.pipi.src.fragment.search

import android.os.Bundle
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
        var detail: TextView? = null
        lateinit var old: Old

        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.search_card_name)
            detail = view.findViewById(R.id.search_card_detail)
            image = view.findViewById(R.id.search_card_image)

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
        holder.detail!!.text = currentItem.oldAddress
        holder.old = oldList[position]
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

}