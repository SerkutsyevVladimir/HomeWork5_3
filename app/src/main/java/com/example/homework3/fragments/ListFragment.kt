package com.example.homework3.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.R
import com.example.homework3.adapters.UserAdapter
import com.example.homework3.databinding.FragmentListBinding
import com.example.homework3.extensions.addPaginationScrollListener
import com.example.homework3.extensions.addSpaceDecoration
import com.example.homework3.retrofit.Item
import com.example.homework3.retrofit.RetrofitService
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        UserAdapter(requireContext()) {
            findNavController().navigate(ListFragmentDirections.toDetails(it.login))
        }
    }

    private var lastId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
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
            swipeLayout.setOnRefreshListener {

                setListofItems()

                Toast.makeText(
                    requireContext(),
                    "List updated",
                    Toast.LENGTH_LONG
                ).show()

                swipeLayout.isRefreshing = false
            }
            val layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            recyclerView.addSpaceDecoration(SPACE_SIZE)

            recyclerView.addPaginationScrollListener(layoutManager, 15) {

                setListofItems()


            }
            toolbar.inflateMenu(R.menu.toolbar_menu)
            toolbar.setOnMenuItemClickListener {
                findNavController().navigate(ListFragmentDirections.toFavorites())
                true
            }
        }

        if (lastId == 0) {
            setListofItems()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        findNavController().navigate(ListFragmentDirections.toFavorites())

        return super.onOptionsItemSelected(item)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
    }

    fun setListofItems() {

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val users = RetrofitService.provideGithubApi().getUsers(lastId)
                if (adapter.currentList.isEmpty()) {
                    adapter.submitList(users + Item.Loading)
                    val currentList = adapter.currentList.toList().dropLast(1)
                    lastId = (currentList.last() as Item.GithubUser).id.toInt()
                } else {
                    val currentList = adapter.currentList.toList().dropLast(1)
                    val resultList = currentList
                        .plus(users)
                        .plus(Item.Loading)
                    adapter.submitList(resultList)
                    val resultListWithoutLoading = resultList.dropLast(1)
                    lastId =
                        (resultListWithoutLoading.last() as Item.GithubUser).id.toInt()
                }
                Log.d("HW5 - call to API", "HW5 - call to API")
            } catch (e: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Uups...Something goes wrong",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}