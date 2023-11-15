package com.practicum.filmoteka_sprint_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val iMDbBaseUrl = "https://imdb-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(iMDbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val iMDbService = retrofit.create(IMDbAPI::class.java)

    private var films = ArrayList<Film>()

    private val adapter = FilmsAdapter(films)

    private lateinit var searchButton: Button
    private lateinit var expression: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var filmList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton = findViewById(R.id.searchButton)
        expression = findViewById(R.id.expression)
        placeholderMessage = findViewById(R.id.placeholderMessage)
        filmList = findViewById(R.id.film_List)

        adapter.filmsList = films

        filmList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        filmList.adapter = adapter

        searchButton.setOnClickListener {
            if (expression.text.isNotEmpty()) search()
        }
    }

    private fun search() {
        iMDbService.getFilms(expression.text.toString())
            .enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(
                    call: Call<FilmsResponse>,
                    response: Response<FilmsResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            if (response.body()?.filmsList?.isNotEmpty() == true) {
                                films.clear()
                                films.addAll(response.body()?.filmsList!!)
                                adapter.notifyDataSetChanged()
//                                showMessage("", "")
                            }
                            if (filmList.isEmpty()) {
                                showMessage(getString(R.string.nothing_found), "")
                            } else {
                                showMessage("", "")
                            }

                        }

                        else -> showMessage(
                            getString(R.string.something_went_wrong),
                            response.code().toString()
                        )
                    }

                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                    showMessage(getString(R.string.something_went_wrong), t.message.toString())
                }

            })
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            films.clear()
            adapter.notifyDataSetChanged()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }


}