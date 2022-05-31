package com.example.homework3.adapters

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.database.AppDatabase
import com.example.homework3.databinding.ItemFavoriteUserBinding
import com.example.homework3.model.GithubFavoriteUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FavoriteUserViewHolder(
    private val binding: ItemFavoriteUserBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root), KoinComponent {

    private val appDatabase by inject<AppDatabase>()

    private val githubDao by lazy {
        appDatabase.githubDao()
    }

    private val scope =
        CoroutineScope(Dispatchers.Main)

    fun bind(user: GithubFavoriteUser) {
        binding.textView.text = user.githubUsername
        binding.root.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this User from Favorites?")
                .setPositiveButton(android.R.string.ok) { dialog, buttonId ->
                     scope.launch {
                    githubDao.delete(user)
                      }
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }
}