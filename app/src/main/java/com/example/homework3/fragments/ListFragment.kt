package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.R
import com.example.homework3.adapters.UserAdapter
import com.example.homework3.databinding.FragmentListBinding
import com.example.homework3.domain.model.Item
import com.example.homework3.domain.repository.UserRemoteRepository
import com.example.homework3.domain.usecase.GetUsersLocalUseCase
import com.example.homework3.domain.usecase.GetUsersUseCase
import com.example.homework3.domain.usecase.InsertUsersLocalUseCase
import com.example.homework3.extensions.addPaginationScrollListener
import com.example.homework3.extensions.addSpaceDecoration
import com.example.homework3.viewmodels.ListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    //private val userRepository by inject<UserRemoteRepository>()

    //private val appDatabase by inject<AppDatabase>()

    private val getUsersUseCase by inject<GetUsersUseCase> ()
    private val insertUsersLocalUseCase by inject<InsertUsersLocalUseCase> ()
    private val getUsersLocalUseCase by inject<GetUsersLocalUseCase>()


    private val viewModel by viewModels<ListViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListViewModel(
                    getUsersUseCase,
                    insertUsersLocalUseCase,
                    getUsersLocalUseCase
                ) as T
            }
        }
    }

    // private val viewModel by viewModel<ListViewModel>()

    private val adapter by lazy {
        UserAdapter(requireContext()) {
            findNavController().navigate(ListFragmentDirections.toDetails(it.login))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addSpaceDecoration(SPACE_SIZE)

            swipeLayout.setOnRefreshListener {

                viewModel.onRefresh()

                viewModel
                    .getData()
                    .onEach {
                        adapter.submitList(it + Item.Loading)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)


                Toast.makeText(
                    requireContext(),
                    "List updated",
                    Toast.LENGTH_LONG
                ).show()

                swipeLayout.isRefreshing = false
            }


            recyclerView.addPaginationScrollListener(layoutManager, 15) {

                viewModel.onLoadMore()
            }
            toolbar.inflateMenu(R.menu.toolbar_menu)
            toolbar.setOnMenuItemClickListener {
                findNavController().navigate(ListFragmentDirections.toFavorites())
                true
            }
        }

        viewModel
            .getData()
            .onEach {
                adapter.submitList(it + Item.Loading)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
    }
}