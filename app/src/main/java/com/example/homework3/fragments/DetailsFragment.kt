package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.homework3.appDatabase
import com.example.homework3.databinding.FragmentDetailsBinding
import com.example.homework3.model.GithubFavoriteUser
import com.example.homework3.retrofit.RetrofitService
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val githubDao by lazy {
        requireContext().appDatabase.githubDao()
    }

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDetailsBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val username = args.username
            textView.text = username

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val user = RetrofitService.provideGithubApi().getUserDetails(username)
                    with(binding) {
                        followers.text = user?.followers.toString()
                        following.text = user?.following.toString()
                        imageDetails.load(user?.avatarUrl)
                    }
                } catch (e: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Uups...Something goes wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            buttonAddFavorites.setOnClickListener {

                textView.text?.takeIf { it.isNotEmpty() }
                    ?.let { username ->
                        // viewLifecycleOwner.lifecycleScope.launch {
                        githubDao.insertAll(GithubFavoriteUser(githubUsername = username.toString()))
                        Toast.makeText(
                            requireContext(),
                            "User successfully added to Favorites",
                            Toast.LENGTH_LONG
                        ).show()
                        // }
                    } ?: run {
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            toolbar.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}