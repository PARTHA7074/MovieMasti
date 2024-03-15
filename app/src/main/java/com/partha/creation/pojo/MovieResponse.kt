package com.partha.creation.pojo

import com.google.gson.annotations.SerializedName
import com.partha.creation.room.entities.Movie

data class MovieResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<Movie?>? = null
)
