package com.osvin.moviestestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.moviestestapp.databinding.ItemMovieBinding
import com.osvin.moviestestapp.models.MovieModel



class MoviesAdapter: PagingDataAdapter<MovieModel, MoviesAdapter.MoviesViewHolder>(MovieDiffCallBack()) {

    class MovieDiffCallBack : DiffUtil.ItemCallback<MovieModel>(){
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

    class MoviesViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

//    //private var movieList: List<MovieModel> = listOf()
//    fun setMovieList(movieModelList: List<MovieModel>){
//       // val diffUtilCallback = MovieModelListComparator(movieList,movieModelList)
//       // val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
//       // this.movieList = movieModelList
//       // diffUtilResult.dispatchUpdatesTo(this)
//        differ.submitList(movieModelList)
//    }

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
        holder.binding.tInfoFilm.text = getItem(position)?.info
        holder.binding.tNameFilm.text = getItem(position)?.name
        Glide.with(holder.itemView)
            .load(getItem(position)?.src)
            .into(holder.binding.filmImage)
    }


}