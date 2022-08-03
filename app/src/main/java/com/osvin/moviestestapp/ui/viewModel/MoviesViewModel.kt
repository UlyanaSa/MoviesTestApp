package com.osvin.moviestestapp.ui.viewModel
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.osvin.moviestestapp.data.ApiMoviesRepository
import com.osvin.moviestestapp.domain.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MovieViewModel
@Inject constructor(private val apiMoviesRepository: ApiMoviesRepository): ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    suspend fun getMovies():Flow<PagingData<MovieModel>>{
        return apiMoviesRepository.getPageMovies().cachedIn(viewModelScope)
    }

}