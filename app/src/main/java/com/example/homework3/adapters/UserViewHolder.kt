package com.example.homework3.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework3.databinding.ItemUserBinding
import com.example.homework3.retrofit.Item

class UserViewHolder(
    private val binding: ItemUserBinding,
    val onUserClicked: (Item.GithubUser) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: Item.GithubUser) {
        with(binding) {
            image.load(user.avatarUrl)
            textView.text = user.login
            root.setOnClickListener {
                onUserClicked(user)
            }
        }
    }
}