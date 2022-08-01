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

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        onError("exceptionHandler: $exception")
    }

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = appRepository.getAllMovies()
            val movieList = ArrayList<MovieModel>()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val result = response.body()!!.results
                    for (index in result.indices) {
                        val movie = MovieModel(
                            name = result[index].displayTitle,
                            info = result[index].summaryShort,
                            src = result[index].multimedia.src
                        )
                        movieList.add(movie)
                    }
                    _loading.postValue(false)
                    _movieListLiveData.postValue(movieList)
                } else {
                    onError("viewModel getAllMovies: ${response.message()}")
                }
            }
        }
    }

    private fun onError(messages: String){
        _errorMessage.value = messages
        _loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}