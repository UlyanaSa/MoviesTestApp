package com.osvin.moviestestapp.ui.viewModel
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.osvin.moviestestapp.AppRepository
import com.osvin.moviestestapp.data.network.ApiMoviesRepository
import com.osvin.moviestestapp.models.MovieModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class MovieViewModel(private val apiMoviesRepository: ApiMoviesRepository): ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    val isErrorEnabled: Flow<Boolean> = apiMoviesRepository.isErrorsEnabled()


    suspend fun getMovies():Flow<PagingData<MovieModel>>{
        return apiMoviesRepository.getPageMovies().cachedIn(viewModelScope)
    }

    fun setEnableErrors(value: Boolean){
        apiMoviesRepository.setErrorsEnabled(value)
    }

}