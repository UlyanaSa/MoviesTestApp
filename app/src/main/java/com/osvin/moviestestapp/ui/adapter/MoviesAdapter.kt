package com.osvin.moviestestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osvin.moviestestapp.databinding.ItemMovieBinding
import com.osvin.moviestestapp.models.MovieModel



class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {



    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>(){
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)



    class MoviesViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    //private var movieList: List<MovieModel> = listOf()
    fun setMovieList(movieModelList: List<MovieModel>){
       // val diffUtilCallback = MovieModelListComparator(movieList,movieModelList)
       // val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
       // this.movieList = movieModelList
       // diffUtilResult.dispatchUpdatesTo(this)
        differ.submitList(movieModelList)
    }

    fun addMovies(movieModelList: List<MovieModel>){
        val newList = differ.currentList + movieModelList
        differ.submitList(newList)
    }

    lateinit var onClickItem: ((MovieModel) -> Unit)

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
        holder.binding.tInfoFilm.text = differ.currentList[position].info
        holder.binding.tNameFilm.text = differ.currentList[position].name
        Glide.with(holder.itemView)
            .load(differ.currentList[position].src)
            .into(holder.binding.filmImage)

        holder.itemView.setOnClickListener {
            onClickItem.invoke(differ.currentList[position])
        }

    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }







}