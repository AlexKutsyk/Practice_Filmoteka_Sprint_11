package com.practicum.filmoteka_sprint_11

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var poster = itemView.findViewById<ImageView>(R.id.poster)
    private var filmName = itemView.findViewById<TextView>(R.id.filmName)
    private var filmInfo = itemView.findViewById<TextView>(R.id.filmInfo)

    fun bind(model: Film) {
        Glide.with(itemView).load(model.image).centerCrop().into(poster)
        filmName.text = model.title
        filmInfo.text = model.description
    }
}