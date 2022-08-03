package com.osvin.moviestestapp.data.network

import com.osvin.moviestestapp.domain.models.MovieModel
import com.osvin.moviestestapp.domain.models.ResponseBodyClass
import com.osvin.moviestestapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("all.json")
    suspend fun getMovieList(@Query("offset") pointOfResultSet: Int, @Query("api-key") apiKey: String):
            Response<ResponseBodyClass.ResponseBody>
}