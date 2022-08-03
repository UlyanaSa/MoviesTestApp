package com.osvin.moviestestapp.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.osvin.moviestestapp.domain.MovieRepository
import com.osvin.moviestestapp.models.MovieModel
import com.osvin.moviestestapp.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.IllegalStateException

class ApiMoviesRepository(private val api: MovieAPI, private val ioDispatcher: CoroutineDispatcher ):MovieRepository {

    private val enableErrorFlow = MutableStateFlow(false)

    override suspend fun getPageMovies(): Flow<PagingData<MovieModel>> {

        val loader: MoviePageLoader = { pageIndex ->
            getAllMovies(pageIndex)
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = {MoviePagingSource(loader, Constants.PAGE_SIZE)}
        ).flow
    }

    override fun isErrorsEnabled(): Flow<Boolean> = enableErrorFlow

    override fun setErrorsEnabled(value: Boolean) {
        enableErrorFlow.value = value
    }

    private suspend fun getAllMovies(pageIndex: Int): List<MovieModel> = withContext(ioDispatcher){

        delay(2000)
        if(enableErrorFlow.value) throw IllegalStateException("Error!")

        val offset = 20*pageIndex
        val response = api.getMovieList(offset, Constants.API_KEY)
        val movieList = ArrayList<MovieModel>()
        if (response.isSuccessful) {
            if (response.body() != null) {
                val result = response.body()!!.results
                for (index in result.indices) {
                    val movie = MovieModel(
                        id = index+offset,
                        name = result[index].displayTitle,
                        info = result[index].summaryShort,
                        src = result[index].multimedia.src
                    )
                    movieList.add(movie)
                }
            }
        }
        return@withContext movieList
    }


}