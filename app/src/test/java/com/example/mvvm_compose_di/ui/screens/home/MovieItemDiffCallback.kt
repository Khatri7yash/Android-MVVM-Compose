package com.example.mvvm_compose_di.ui.screens.home

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvm_compose_di.data.model.MovieItem

class MovieItemDiffCallback: DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(
        oldItem: MovieItem,
        newItem: MovieItem
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieItem,
        newItem: MovieItem
    ): Boolean = oldItem == newItem
}