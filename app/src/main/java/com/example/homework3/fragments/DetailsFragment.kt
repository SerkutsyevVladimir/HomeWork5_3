package com.example.homework3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.homework3.databinding.FragmentDetailsBinding
import com.example.homework3.retrofit.GithubUserDetails
import com.example.homework3.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

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
            RetrofitService.provideGithubApi().getUserDetails(username)
                .enqueue(object : Callback<GithubUserDetails> {
                    override fun onResponse(
                        call: Call<GithubUserDetails>,
                        response: Response<GithubUserDetails>
                    ) {
                        if (response.isSuccessful) {
                            val user = response.body()
                            with(binding) {
                                followers.text = user?.followers.toString()
                                following.text = user?.following.toString()
                                imageDetails.load(user?.avatarUrl)
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Uups...Something goes wrong",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<GithubUserDetails>, t: Throwable) {
                        Toast.makeText(
                            requireContext(),
                            "Uups...Something goes wrong2",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                )

            toolbar.setOnClickListener { findNavController().popBackStack() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}