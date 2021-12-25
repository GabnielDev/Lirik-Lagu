package com.example.liriklagu.data

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: ArrayList<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("songTitle")
	val songTitle: String? = null,

	@field:SerializedName("songLyrics")
	val songLyrics: String? = null,

	@field:SerializedName("artist")
	val artist: String? = null,

	@field:SerializedName("songId")
	val songId: String? = null
)
