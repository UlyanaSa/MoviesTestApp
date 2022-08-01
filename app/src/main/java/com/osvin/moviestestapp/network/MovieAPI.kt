package com.osvin.moviestestapp.network

import com.osvin.moviestestapp.models.ResponseBodyClass
import com.osvin.moviestestapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("all.json")
    suspend fun getMovieList(@Query("offset") pointOfResultSet: Int, @Query("api-key") apiKey: String):
            Response<ResponseBodyClass.ResponseBody>

    companion object{
        fun getInstance(): MovieAPI {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieAPI::class.java)
        }
    }
}