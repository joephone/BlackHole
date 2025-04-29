package com.transcendence.freeland.ui.rv.vp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.transcendence.freeland.R

class FragmentAdapter(var context: Context) : RecyclerView.Adapter<FragmentAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.activity_ui_rv_vp_rv_item, parent, false)
        return Holder(inflate)
    }

    override fun getItemCount(): Int {
        //模拟30条数据
        return 30
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.testText.text = "模拟数据position=$position"
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val testText: TextView = itemView.findViewById(R.id.tv_title)
    }
}