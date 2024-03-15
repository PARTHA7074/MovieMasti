package com.partha.creation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.partha.creation.R
import com.partha.creation.activities.MainActivity
import com.partha.creation.databinding.FragmentMovieDetailsBinding
import com.partha.creation.room.AppDatabase
import com.partha.creation.room.RoomRepository
import com.partha.creation.room.entities.Movie
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private var movie: Movie? = null
    private var roomRepository: RoomRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).navigationViewVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        roomRepository = RoomRepository(AppDatabase.getDatabase(requireContext()))

        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("Movie", Movie::class.java)
        } else Movie()

        Picasso.get().load(movie?.thumb).into(binding.thumb)
        binding.title.text = movie?.title
        binding.subtitle.text = movie?.subTitle
        binding.description.text = movie?.summary
        updateFavButton()

        binding.favBtn.setOnClickListener {
            movie?.isFavorite = movie?.isFavorite != true
            roomRepository?.updateMovieFavoriteStatus(movie?.id!!, movie?.isFavorite == true)
            updateFavButton()
            Toast.makeText(context, if (movie?.isFavorite == true) "Added to favorite" else "Removed from favorite" , Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun updateFavButton(){
        binding.favBtn.setImageResource(if (movie?.isFavorite == true) R.drawable.favorite_red else R.drawable.favorite_32)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as MainActivity).navigationViewVisibility(true)
    }

}