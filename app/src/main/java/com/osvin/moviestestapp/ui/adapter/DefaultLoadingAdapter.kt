package com.osvin.moviestestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingSource
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.osvin.moviestestapp.databinding.DefaultLoaderBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadingAdapter(private val tryAgainAction: TryAgainAction):LoadStateAdapter<DefaultLoadingAdapter.Holder>() {




    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DefaultLoaderBinding.inflate(layoutInflater,parent,false)
        return Holder(binding, null, tryAgainAction)
    }

    class Holder(
        private val binding: DefaultLoaderBinding,
        private val swipeRefreshLayout: SwipeRefreshLayout?,
        private val tryAgainAction: TryAgainAction
    ):RecyclerView.ViewHolder(binding.root) {
        init {
            binding.button.setOnClickListener{
                tryAgainAction()
            }
        }

        fun bind(loadState: LoadState) = with(binding){
            tError.isVisible = loadState is LoadState.Error
            button.isVisible = loadState is LoadState.Error
            if(swipeRefreshLayout != null){
                swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
                progressBar.isVisible = false
            }else{
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }

}