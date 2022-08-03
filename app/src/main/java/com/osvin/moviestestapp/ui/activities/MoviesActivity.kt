package com.osvin.moviestestapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.osvin.moviestestapp.data.network.ApiMoviesRepository
import com.osvin.moviestestapp.databinding.ActivityMoviesBinding
import com.osvin.moviestestapp.data.network.MovieAPI
import com.osvin.moviestestapp.ui.adapter.DefaultLoadingAdapter
import com.osvin.moviestestapp.ui.adapter.MoviesAdapter
import com.osvin.moviestestapp.ui.adapter.TryAgainAction
import com.osvin.moviestestapp.ui.viewModel.MovieViewModel
import com.osvin.moviestestapp.ui.viewModel.MovieViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


class MoviesActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var mainStateHolder: DefaultLoadingAdapter.Holder




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = MovieAPI.getInstance()
        val ioDisp = Dispatchers.IO
        val apiMoviesRepository = ApiMoviesRepository(api, ioDisp)
        movieViewModel = ViewModelProvider(this, MovieViewModelFactory(apiMoviesRepository))[MovieViewModel::class.java]

        moviesAdapter = MoviesAdapter()
        val tryAgainAction: TryAgainAction = {moviesAdapter.retry()}
        val footerLoadingAdapter = DefaultLoadingAdapter(tryAgainAction)
        val combinedAdapters = moviesAdapter.withLoadStateFooter(footerLoadingAdapter)
        binding.rvMovieList.adapter = combinedAdapters
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)

        collectUiState()
        observeLoadState()

        mainStateHolder = DefaultLoadingAdapter.Holder(
            binding.defaultLoader,
            binding.swipeRefreshLayout,
            tryAgainAction
        )
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.debounce(200).collectLatest {
                mainStateHolder.bind(it.refresh)
            }
        }
    }

    private fun collectUiState() {
        lifecycleScope.launch{
            movieViewModel.getMovies().collectLatest {
                moviesAdapter.submitData(it)
            }
        }
    }



}