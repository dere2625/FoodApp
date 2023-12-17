package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.models.AboutMeData

class AboutMeAdapter(private val dataList: List<AboutMeData>) :
    RecyclerView.Adapter<AboutMeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.about_me_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.culinary.text =  "Culinary : ${ currentItem.culinary}"
        holder.favorite.text = "Favorite : ${ currentItem.favorite}"
        holder.food.text = "Food : ${ currentItem.food}"

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val culinary: TextView = itemView.findViewById(R.id.textculinary)
        val favorite: TextView = itemView.findViewById(R.id.textViewfavorite)
        val food: TextView = itemView.findViewById(R.id.textViewFood)
    }
}
