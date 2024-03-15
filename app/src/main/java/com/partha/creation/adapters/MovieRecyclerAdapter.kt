package com.partha.creation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.partha.creation.R
import com.partha.creation.databinding.MovieRecyclerItemBinding
import com.partha.creation.fragments.FavoriteFragment
import com.partha.creation.fragments.MovieFragment
import com.partha.creation.room.entities.Movie
import com.squareup.picasso.Picasso

class MovieRecyclerAdapter(private val fragment: Fragment, private val data: List<Movie?>?) : RecyclerView.Adapter<MovieRecyclerAdapter.MyViewHolder>()  {
    inner class MyViewHolder(val binding: MovieRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MovieRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie =data?.get(position)
        Picasso.get().load(movie?.thumb).into(holder.binding.movieThumb)

        holder.binding.movieThumb.setOnClickListener{
            if (fragment is MovieFragment)
                fragment.findNavController().navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundleOf("Movie" to movie))
            else if (fragment is FavoriteFragment)
                fragment.findNavController().navigate(R.id.action_favoritesFragment_to_movieDetailsFragment, bundleOf("Movie" to movie))
        }
        fragment.findNavController()
    }

    override fun getItemCount(): Int {
        return data?.size?: 0
    }
}