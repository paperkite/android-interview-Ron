package com.example.interview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PageViewAdaptor (private val dataList: ArrayList<String>, private val layoutVal: Int) : RecyclerView.Adapter<PageViewAdaptor.PageViewHolder>() {

    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(layoutVal, parent, false)
        return PageViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.itemTxt)
        textView.text = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}