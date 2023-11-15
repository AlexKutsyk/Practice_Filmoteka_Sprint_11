package com.practicum.filmoteka_sprint_11

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbAPI {
    @GET ("/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun getFilms(@Path("expression") expression: String) : Call<FilmsResponse>
}