package com.example.homework3.adapters

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.appDatabase
import com.example.homework3.databinding.ItemFavoriteUserBinding
import com.example.homework3.model.GithubFavoriteUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteUserViewHolder(
    private val binding: ItemFavoriteUserBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private val githubDao by lazy {
        context.appDatabase.githubDao()
    }

//    private val scope =
//        CoroutineScope(Dispatchers.Main)

    fun bind(user: GithubFavoriteUser) {
        binding.textView.text = user.githubUsername
        binding.root.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this User from Favorites?")
                .setPositiveButton(android.R.string.ok) { dialog, buttonId ->
                   // scope.launch {
                        githubDao.delete(user)
                  //  }
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }
}