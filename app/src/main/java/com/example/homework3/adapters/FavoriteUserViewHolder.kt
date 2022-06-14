package com.example.homework3.adapters

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3.databinding.ItemFavoriteUserBinding
import com.example.homework3.domain.model.FavoriteUser
import com.example.homework3.domain.usecase.DeleteFavoriteUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FavoriteUserViewHolder(
    private val binding: ItemFavoriteUserBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root), KoinComponent {


    private val deleteFavoriteUserUseCase by inject<DeleteFavoriteUserUseCase>()

    private val scope =
        CoroutineScope(Dispatchers.Main)

    fun bind(user: FavoriteUser) {
        binding.textView.text = user.username
        binding.root.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this User from Favorites?")
                .setPositiveButton(android.R.string.ok) { dialog, buttonId ->
                    scope.launch {
                        deleteFavoriteUserUseCase(user)
                    }
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }
}