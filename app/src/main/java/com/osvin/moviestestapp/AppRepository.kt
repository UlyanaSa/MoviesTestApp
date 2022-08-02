package com.osvin.moviestestapp

import android.content.Context
import com.osvin.moviestestapp.data.network.MovieAPI
import com.osvin.moviestestapp.data.storage.SharedPreferences
import com.osvin.moviestestapp.utils.Constants

class AppRepository(private val api: MovieAPI) {



    suspend fun getAllMovies(offset: Int) = api.getMovieList(offset, Constants.API_KEY)
}