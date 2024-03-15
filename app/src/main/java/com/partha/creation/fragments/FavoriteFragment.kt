package com.partha.creation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.partha.creation.adapters.MovieRecyclerAdapter
import com.partha.creation.databinding.FragmentFavoritesBinding
import com.partha.creation.room.AppDatabase
import com.partha.creation.room.RoomRepository
import com.partha.creation.room.entities.Movie


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private var movieList: ArrayList<Movie?>? = ArrayList()
    private var roomRepository: RoomRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        roomRepository = RoomRepository(AppDatabase.getDatabase(requireContext()))

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.movieRecyclerView.adapter = MovieRecyclerAdapter(this, movieList)

        return binding.root
    }

    private fun updateMovieList(){
        val allMovies = roomRepository?.getAllMovies()
        movieList?.clear()
        if (allMovies != null) {
            for (ele in allMovies){
                if (ele.isFavorite) {
                    movieList?.add(ele)
                }
            }
        }
        binding.emptyTxt.isVisible = movieList?.isEmpty() == true
        binding.movieRecyclerView.adapter?.notifyItemRangeRemoved(0,movieList?.size?:0)
    }

    override fun onResume() {
        super.onResume()
        updateMovieList()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}