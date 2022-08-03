package com.osvin.moviestestapp.domain
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.osvin.moviestestapp.domain.models.MovieModel
import java.lang.Exception




class MoviePagingSource(private val loader:MoviePageLoader):
    PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val pageIndex = params.key ?: 0
        return try {
            val movies = loader.invoke(pageIndex)
            return LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 0) null else pageIndex-1,
                nextKey = pageIndex + 1
            )
        }catch (e:Exception){
            LoadResult.Error(
                throwable = e
            )
        }
    }

}

typealias MoviePageLoader = suspend (pageIndex: Int) -> List<MovieModel>

