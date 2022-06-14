package com.example.homework3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.ItemFavoriteUserBinding
import com.example.homework3.domain.model.FavoriteUser

class FavoriteUserAdapter(
    context: Context
) : ListAdapter<FavoriteUser, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        return FavoriteUserViewHolder(
            binding = ItemFavoriteUserBinding.inflate(
                layoutInflater,
                parent,
                false
            ), context
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userVH = holder as FavoriteUserViewHolder
        val user = getItem(position) as FavoriteUser
        userVH.bind(user)
    }


    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<FavoriteUser>() {
            override fun areItemsTheSame(
                oldItem: FavoriteUser,
                newItem: FavoriteUser
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavoriteUser,
                newItem: FavoriteUser
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}