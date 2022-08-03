package com.osvin.moviestestapp.data
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.osvin.moviestestapp.data.network.MovieAPI
import com.osvin.moviestestapp.domain.models.MovieModel
import com.osvin.moviestestapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiMoviesRepository
@Inject constructor(private val api: MovieAPI){

    suspend fun getPageMovies(): Flow<PagingData<MovieModel>> {

        val loader: MoviePageLoader = { pageIndex ->
            getAllMovies(pageIndex)
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 1
            ),
            pagingSourceFactory = { MoviePagingSource(loader) }
        ).flow
    }


    private suspend fun getAllMovies(pageIndex: Int): List<MovieModel> = withContext(Dispatchers.IO){

        delay(2000)
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