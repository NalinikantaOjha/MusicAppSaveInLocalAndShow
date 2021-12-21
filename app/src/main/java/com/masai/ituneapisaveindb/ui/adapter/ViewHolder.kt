package com.masai.ituneapisaveindb.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.ituneapisaveindb.R
import com.masai.ituneapisaveindb.local.MusicEntity
import com.masai.ituneapisaveindb.remote.Result
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View, val onClick: OnClick) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun setData(result: MusicEntity) {

        itemView.apply {

            tvTitle.setText(result.artistName)
            Glide.with(ivImageView).load(result.artistImageUrl)
                .into(ivImageView)
        }

    }
}