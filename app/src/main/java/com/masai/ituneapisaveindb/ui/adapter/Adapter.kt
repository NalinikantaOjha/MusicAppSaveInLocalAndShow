package com.masai.ituneapisaveindb.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.ituneapisaveindb.R
import com.masai.ituneapisaveindb.local.MusicEntity
import com.masai.ituneapisaveindb.remote.Result


class Adapter(
    var list: MutableList<MusicEntity>,
    val onClick: OnClick,
    var context: Context
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view1, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}