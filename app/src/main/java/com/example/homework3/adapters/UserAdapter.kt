package com.example.homework3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.ItemLoadingBinding
import com.example.homework3.databinding.ItemUserBinding
import com.example.homework3.domain.model.Item


class UserAdapter(
    context: Context,
    private val onUserClicked: (Item.User) -> Unit
) : ListAdapter<Item, RecyclerView.ViewHolder>(DIFF_UTIL) {
    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Item.User -> TYPE_USER
            Item.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_USER -> {
                UserViewHolder(
                    binding = ItemUserBinding.inflate(layoutInflater, parent, false),
                    onUserClicked = onUserClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            else -> error("Incorrect viewType = $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userVH = holder as? UserViewHolder ?: return
        val item = getItem(position) as? Item.User ?: return
        userVH.bind(item)
    }

    companion object {

        private const val TYPE_USER = 0
        private const val TYPE_LOADING = 1


        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}