package com.partha.creation.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.partha.creation.adapters.MovieRecyclerAdapter
import com.partha.creation.databinding.FragmentMovieBinding
import com.partha.creation.retrofit.MyViewModel
import com.partha.creation.room.AppDatabase
import com.partha.creation.room.RoomRepository
import com.partha.creation.room.entities.Movie
import com.partha.creation.utils.Constants
import com.partha.creation.utils.NetworkManager

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by viewModels()
    private var roomRepository: RoomRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        roomRepository = RoomRepository(AppDatabase.getDatabase(requireContext()))

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)


        viewModel.movieResponse.observe(viewLifecycleOwner) { movieResponse ->
            binding.progressBar.isVisible = false
            Constants.movies = movieResponse.data
            binding.movieRecyclerView.adapter = MovieRecyclerAdapter(this, movieResponse.data)
            if (movieResponse.data?.isNotEmpty() == true) roomRepository?.deleteAllMovies()
            roomRepository?.insertAllMovies(movieResponse.data as List<Movie>?)
        }

        Constants.isInternetActive = NetworkManager().isInternetAvailable(requireContext())
        if (!Constants.isInternetActive) {
            Constants.movies = roomRepository?.getAllMovies()
        }

        if (Constants.movies == null || Constants.movies?.isEmpty() == true) {
            viewModel.fetchMovies("1")
        } else {
            binding.movieRecyclerView.adapter = MovieRecyclerAdapter(this, Constants.movies)
            binding.progressBar.isVisible = false
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}