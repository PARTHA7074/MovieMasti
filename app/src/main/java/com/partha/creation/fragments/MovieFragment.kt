package com.partha.creation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.partha.creation.R
import com.partha.creation.adapters.MovieRecyclerAdapter
import com.partha.creation.databinding.FragmentMovieBinding
import com.partha.creation.retrofit.MyViewModel
import com.partha.creation.utils.Constants

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)

        viewModel.movieResponse.observe(viewLifecycleOwner) { movieResponse ->
            // Update UI with the movie response
            // Example: binding.textView.text = movieResponse.someValue
            // Clear any error message if movieResponse is received successfully
            // Example: binding.errorTextView.visibility = View.GONE
            Log.d("onResponse: ", movieResponse.toString())
            Constants.data = movieResponse.data
            binding.movieRecyclerView.adapter = MovieRecyclerAdapter(this,movieResponse.data)

        }
        if (Constants.data == null) viewModel.fetchMovies("1")

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}