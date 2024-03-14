package com.partha.creation.pojo

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.createStringArrayList(),
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString(),
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString(),
		parcel.createStringArrayList()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(summary)
		parcel.writeString(subTitle)
		parcel.writeString(thumb)
		parcel.writeValue(nsfw)
		parcel.writeValue(totalChapter)
		parcel.writeString(title)
		parcel.writeString(type)
		parcel.writeStringList(genres)
		parcel.writeValue(updateAt)
		parcel.writeString(id)
		parcel.writeValue(createAt)
		parcel.writeString(status)
		parcel.writeStringList(authors)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<DataItem> {
		override fun createFromParcel(parcel: Parcel): DataItem {
			return DataItem(parcel)
		}

		override fun newArray(size: Int): Array<DataItem?> {
			return arrayOfNulls(size)
		}
	}
}
