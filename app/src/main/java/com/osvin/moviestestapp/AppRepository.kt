package com.osvin.moviestestapp

import com.osvin.moviestestapp.network.MovieAPI
import com.osvin.moviestestapp.utils.Constants

class AppRepository(private val api: MovieAPI, private val offset: Int) {


//    fun getCurrentOffset(): Int {
//        return movieStorage.getCurrentOffset()
//    }
//
//    fun saveCurrentOffset(offset: Int): Boolean {
//        return movieStorage.saveOffset(offset)
//    }

    suspend fun getAllMovies() = api.getMovieList(offset, Constants.API_KEY)
}