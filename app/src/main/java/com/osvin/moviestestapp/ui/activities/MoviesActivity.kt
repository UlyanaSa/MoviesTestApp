package com.osvin.moviestestapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.osvin.moviestestapp.AppRepository
import com.osvin.moviestestapp.databinding.ActivityMoviesBinding
import com.osvin.moviestestapp.models.MovieModel
import com.osvin.moviestestapp.network.MovieAPI
import com.osvin.moviestestapp.ui.adapter.MoviesAdapter
import com.osvin.moviestestapp.ui.viewModel.MovieViewModel
import com.osvin.moviestestapp.ui.viewModel.MovieViewModelFactory

class MoviesActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val api = MovieAPI.getInstance()
        val offset = 0
        val appRepository = AppRepository(api, offset)


        moviesAdapter = MoviesAdapter()
        binding.rvMovieList.adapter = moviesAdapter
        binding.rvMovieList?.layoutManager = LinearLayoutManager(this)
        movieViewModel = ViewModelProvider(this, MovieViewModelFactory(appRepository))[MovieViewModel::class.java]
        movieViewModel.getAllMovies()

        movieViewModel.movieListLiveData.observe(this, Observer {
            moviesAdapter.setMovieList(it)
        })

        movieViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            binding.errorText?.visibility = View.VISIBLE
        })

        movieViewModel.loading.observe(this, Observer {
            binding.apply {
                if(it){
                    progressBar.visibility = View.VISIBLE
                }else{
                    progressBar.visibility = View.GONE
                }
            }
        })


    }



}