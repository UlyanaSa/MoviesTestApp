package com.osvin.moviestestapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvin.moviestestapp.AppRepository
import java.security.InvalidParameterException

class MovieViewModelFactory(private val appRepository: AppRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(appRepository) as T
        }
        throw InvalidParameterException("ViewModel Not Found")
    }

}