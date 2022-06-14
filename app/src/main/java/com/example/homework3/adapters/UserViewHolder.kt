package com.example.homework3.adapters

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework3.databinding.ItemUserBinding
import com.example.homework3.domain.model.Item

class UserViewHolder(
    private val binding: ItemUserBinding,
    val onUserClicked: (Item.User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: Item.User) {
        with(binding) {
            image.load(user.avatarUrl)
            textView.text = user.login
            root.setOnClickListener {
                onUserClicked(user)
            }
        }
    }
}