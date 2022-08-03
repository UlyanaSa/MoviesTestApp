package com.osvin.moviestestapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvin.moviestestapp.data.ApiMoviesRepository
import java.security.InvalidParameterException

//class MovieViewModelFactory(private val apiMoviesRepository: ApiMoviesRepository): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
//            return MovieViewModel(apiMoviesRepository) as T
//        }
//        throw InvalidParameterException("ViewModel Not Found")
//    }
//
//}