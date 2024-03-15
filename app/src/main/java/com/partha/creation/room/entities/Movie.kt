package com.partha.creation.room.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(

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

	@field:SerializedName("update_at")
	val updateAt: Long? = null,

	@field:SerializedName("id")
	@PrimaryKey(autoGenerate = false)
	var id: String = "default",

	@field:SerializedName("create_at")
	val createAt: Long? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("is_favorite")
	var isFavorite: Boolean = false
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString()?:"",
		parcel.readValue(Long::class.java.classLoader) as? Long,
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as Boolean
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
		parcel.writeValue(updateAt)
		parcel.writeString(id)
		parcel.writeValue(createAt)
		parcel.writeString(status)
		parcel.writeValue(isFavorite)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Movie> {
		override fun createFromParcel(parcel: Parcel): Movie {
			return Movie(parcel)
		}

		override fun newArray(size: Int): Array<Movie?> {
			return arrayOfNulls(size)
		}
	}
}