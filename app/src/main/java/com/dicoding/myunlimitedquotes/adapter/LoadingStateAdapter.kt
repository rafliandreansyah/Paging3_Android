package com.dicoding.myunlimitedquotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.myunlimitedquotes.databinding.LoadingItemBinding

class LoadingStateAdapter(private val retry: () -> Unit): LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    inner class LoadingStateViewHolder(private val binding: LoadingItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    errorMsg.text = loadState.error.message
                }
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible = loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val view = LoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(view)
    }

}