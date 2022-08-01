package com.osvin.moviestestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.moviestestapp.databinding.ItemMovieBinding
import com.osvin.moviestestapp.models.MovieModel

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    private var movieList: List<MovieModel> = listOf()
    fun setMovieList(movieModelList: List<MovieModel>){
        //   val diffUtilCallback = MovieModelListComparator(movieList,movieModelList)
        //    val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.movieList = movieModelList
        //  diffUtilResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.tInfoFilm.text = movieList[position].info
        holder.binding.tNameFilm.text = movieList[position].name
        Glide.with(holder.itemView)
            .load(movieList[position].src)
            .into(holder.binding.filmImage)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}