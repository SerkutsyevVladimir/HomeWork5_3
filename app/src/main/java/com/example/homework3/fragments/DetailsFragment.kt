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
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.homework3.database.AppDatabase
import com.example.homework3.databinding.FragmentDetailsBinding
import com.example.homework3.model.GithubFavoriteUser
import com.example.homework3.retrofit.GithubUserDetails
import com.example.homework3.retrofit.UserRepository
import com.example.homework3.viewmodels.DetailsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val userRepository by inject<UserRepository>()

    private val appDatabase by inject<AppDatabase>()

    private val viewModel by viewModels<DetailsViewModel> {
        object : ViewModelProvider.Factory {
            @Suppress
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetailsViewModel(
                    userRepository,
                    appDatabase.githubDao()
                ) as T
            }
        }
    }

    private val args by navArgs<DetailsFragmentArgs>()

//    private val viewModel2 by viewModel<DetailsViewModel>{
//        parametersOf(args.username)
//    }

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

            textView.text = args.username

            try {
                var user: GithubUserDetails
                viewModel.getUserDetails(args.username)
             //   viewModel2.getDetails
                    .onEach {
                        user = it
                        with(binding) {
                            followers.text = user.followers.toString()
                            following.text = user?.following.toString()
                            imageDetails.load(user?.avatarUrl)
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)

            } catch (e: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Uups...Something goes wrong",
                    Toast.LENGTH_LONG
                ).show()
            }

            buttonAddFavorites.setOnClickListener {

                textView.text?.takeIf { it.isNotEmpty() }
                    ?.let { username ->
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.insertAll(GithubFavoriteUser(githubUsername = username.toString()))
                            Toast.makeText(
                                requireContext(),
                                "User successfully added to Favorites",
                                Toast.LENGTH_LONG
                            ).show()
                        }
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