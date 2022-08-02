package com.osvin.moviestestapp.domain

import androidx.paging.PagingData
import com.osvin.moviestestapp.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPageMovies(): Flow<PagingData<MovieModel>>

    fun isErrorsEnabled():Flow<Boolean>

    fun setErrorsEnabled(value: Boolean)

}