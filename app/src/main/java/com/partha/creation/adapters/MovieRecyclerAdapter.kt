package com.partha.creation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.partha.creation.R
import com.partha.creation.databinding.MovieRecyclerItemBinding
import com.partha.creation.pojo.DataItem
import com.squareup.picasso.Picasso

class MovieRecyclerAdapter(private val fragment: Fragment, private val data: List<DataItem?>?) : RecyclerView.Adapter<MovieRecyclerAdapter.MyViewHolder>()  {
    inner class MyViewHolder(val binding: MovieRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MovieRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie =data?.get(position)
        Picasso.get().load(movie?.thumb).into(holder.binding.movieThumb)

        holder.binding.movieThumb.setOnClickListener{
            fragment.findNavController().navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundleOf("Movie" to movie))
        }
    }

    override fun getItemCount(): Int {
        return data?.size?: 0
    }
}