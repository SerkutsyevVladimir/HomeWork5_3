package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.adapters.FavoriteUserAdapter
import com.example.homework3.database.AppDatabase
import com.example.homework3.databinding.FragmentFavoritesListBinding
import com.example.homework3.extensions.addSpaceDecoration
import com.example.homework3.viewmodels.DetailsViewModel
import com.example.homework3.viewmodels.FavoriteListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesListFragment : Fragment() {
    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val appDatabase by inject<AppDatabase>()

    private val viewModel by viewModels<FavoriteListViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FavoriteListViewModel(
                    appDatabase.githubDao()
                ) as T
            }
        }
    }

 //   private val viewModel by viewModel<FavoriteListViewModel>()


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
                recyclerView.adapter = adapter
                recyclerView.addSpaceDecoration(SPACE_SIZE)
                viewModel.getAll().onEach {
                    adapter.submitList(it)
                }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
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