package com.example.practice.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.Model.BGDataClass
import com.example.practice.R

class BGAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private var bgData:List<BGDataClass> = emptyList()
    fun setData(data:List<BGDataClass>){
        this.bgData = data
    }

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = bgData[position].name
        holder.location.text = bgData[position].location
        holder.feature.text = bgData[position].feature
        Glide.with(context).load(bgData[position].url).centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return bgData.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val location: TextView = itemView.findViewById(R.id.location)
    val feature: TextView = itemView.findViewById(R.id.feature)
    val image: ImageView = itemView.findViewById(R.id.imageView)
}