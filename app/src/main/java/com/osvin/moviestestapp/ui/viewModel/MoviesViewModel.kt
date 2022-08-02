package com.osvin.moviestestapp.ui.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osvin.moviestestapp.AppRepository
import com.osvin.moviestestapp.models.MovieModel
import kotlinx.coroutines.*

class MovieViewModel(private val appRepository: AppRepository): ViewModel() {
    companion object {
        const val TAG = "TAG"
    }

    private var _movieListLiveData = MutableLiveData<ArrayList<MovieModel>>()
    private var _errorMessage = MutableLiveData<String>()
    private var _loading = MutableLiveData<Boolean>()


    private var job: Job? = null

    val movieListLiveData: LiveData<ArrayList<MovieModel>> = _movieListLiveData
    val errorMessage: LiveData<String> = _errorMessage
    val loading: LiveData<Boolean> = _loading






    private fun onError(messages: String){
        _errorMessage.value = messages
        _loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}