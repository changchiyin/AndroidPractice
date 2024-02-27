package com.example.practice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    private var myData:List<BGDataClass> = emptyList()
    fun setMyData(data:List<BGDataClass>){
        this.myData = data
    }

    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = myData[position].name
        holder.location.text = myData[position].location
        holder.feature.text = myData[position].feature
        Glide.with(context).load(myData[position].url).centerCrop().into(holder.image)
    }

    override fun getItemCount(): Int {
        return myData.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val location: TextView = itemView.findViewById(R.id.location)
    val feature: TextView = itemView.findViewById(R.id.feature)
    val image: ImageView = itemView.findViewById(R.id.imageView)
}