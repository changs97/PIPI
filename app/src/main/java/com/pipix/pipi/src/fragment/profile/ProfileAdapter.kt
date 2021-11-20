package com.pipix.pipi.src.fragment.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pipix.pipi.R
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.src.fragment.profile.ProfileFragment.Companion.dataList
import com.pipix.pipi.src.main.MainActivity

class ProfileAdapter :  RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var delete: TextView? = null
        var dateTime: TextView? = null
        var itemPosition : Int? = null
        var type: TextView? = null
        lateinit var data : PureResult


        init {
            // Define click listener for the ViewHolder's View.
            delete = view!!.findViewById(R.id.item_delete)
            dateTime = view!!.findViewById(R.id.item_time)
            type = view!!.findViewById(R.id.item_type)


            view.setOnClickListener {

                val action = ProfileFragmentDirections.actionProfileFragmentToChartFragment(data)
                Navigation.findNavController(view).navigate(action)

            }


/*
            more!!.setOnClickListener {
                val intent2 = Intent(view.context, DeleteItemActivity::class.java)
                intent2.putExtra("delete",itemPosition)
                view.getContext().startActivity(intent2)
            }
            */


        }
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.profile_item, viewGroup, false)

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
        val time = currentItem.date
        viewHolder.dateTime?.setText(time.toString())
        viewHolder.data = dataList[position]
        viewHolder.itemPosition = position

    }



    fun setData(user : List<PureResult>){
        dataList = user
        notifyDataSetChanged()
    }

}

