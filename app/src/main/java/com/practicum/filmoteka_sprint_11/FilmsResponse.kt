package com.practicum.filmoteka_sprint_11

class FilmsResponse (val searchType: String,
                     val expression: String,
                     val filmsList: ArrayList<Film>)