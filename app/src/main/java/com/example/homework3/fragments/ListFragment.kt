package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework3.adapters.UserAdapter
import com.example.homework3.databinding.FragmentListBinding
import com.example.homework3.extensions.addPaginationScrollListener
import com.example.homework3.extensions.addSpaceDecoration
import com.example.homework3.retrofit.Item
import com.example.homework3.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val adapter by lazy {
        UserAdapter(requireContext()) {
            findNavController().navigate(ListFragmentDirections.toDetails(it.login))
        }
    }

    private var lastId = 0

    private var currentCall: Call<List<Item.GithubUser>>? = null

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
                if (currentCall == null) {

                    currentCall = RetrofitService.provideGithubApi().getUsers(lastId)
                    currentCall?.enqueue(object : Callback<List<Item.GithubUser>> {
                        override fun onResponse(
                            call: Call<List<Item.GithubUser>>,
                            response: Response<List<Item.GithubUser>>
                        ) {
                            if (response.isSuccessful) {
                                val users = response.body() ?: return
                                val currentList = adapter.currentList.toList().dropLast(1)
                                val resultList = currentList
                                    .plus(users)
                                    .plus(Item.Loading)
                                adapter.submitList(resultList)
                                val resultListWithoutLoading = resultList.dropLast(1)
                                lastId =
                                    (resultListWithoutLoading.last() as Item.GithubUser).id.toInt()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Uups...Something goes wrong",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            currentCall = null
                        }

                        override fun onFailure(
                            call: Call<List<Item.GithubUser>>,
                            t: Throwable
                        ) {
                            currentCall = null
                        }


                    })
                }
            }
        }

        setListofItems()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        currentCall?.cancel()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50
    }

    fun setListofItems() {
        if (currentCall == null) {
            if (lastId != 0) {
                lastId = 0
                binding.recyclerView.smoothScrollToPosition(0)
            }
            currentCall = RetrofitService.provideGithubApi().getUsers(lastId)
            currentCall?.enqueue(object : Callback<List<Item.GithubUser>> {
                override fun onResponse(
                    call: Call<List<Item.GithubUser>>,
                    response: Response<List<Item.GithubUser>>
                ) {
                    if (response.isSuccessful) {
                        val users = response.body() ?: return
                        adapter.submitList(users + Item.Loading)
                        val currentList = adapter.currentList.toList().dropLast(1)
                        lastId = (currentList.last() as Item.GithubUser).id.toInt()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Uups...Something goes wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    currentCall = null
                }

                override fun onFailure(call: Call<List<Item.GithubUser>>, t: Throwable) {
                    currentCall = null
                    Toast.makeText(
                        requireContext(),
                        "Uups...Something goes wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }


            })
        }
    }

}