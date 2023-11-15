package com.practicum.filmoteka_sprint_11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmsAdapter(var filmsList: ArrayList<Film>) : RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.film_list_item, parent, false)
        return FilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

}