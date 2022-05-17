package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.adapters.FavoriteUserAdapter
import com.example.homework3.appDatabase
import com.example.homework3.databinding.FragmentFavoritesListBinding
import com.example.homework3.extensions.addSpaceDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesListFragment : Fragment() {
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val githubDao by lazy {
        requireContext().appDatabase.githubDao()
    }

    private val adapter by lazy {
        FavoriteUserAdapter(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentFavoritesListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            viewLifecycleOwner.lifecycleScope.launch {
                val favoriteUsers = githubDao.getAll().toList()
                recyclerView.adapter = adapter
                recyclerView.addSpaceDecoration(SPACE_SIZE)
                adapter.submitList(favoriteUsers)
                toolbar.setOnClickListener { findNavController().popBackStack() }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50

    }
}