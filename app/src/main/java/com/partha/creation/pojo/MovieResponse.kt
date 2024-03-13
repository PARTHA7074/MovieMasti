package com.partha.creation.pojo

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("sub_title")
	val subTitle: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("nsfw")
	val nsfw: Boolean? = null,

	@field:SerializedName("total_chapter")
	val totalChapter: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("update_at")
	val updateAt: Long? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("create_at")
	val createAt: Long? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("authors")
	val authors: List<String?>? = null
)
