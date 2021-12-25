package com.example.liriklagu.data

import com.google.gson.annotations.SerializedName

data class Lyric(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Data(

	@field:SerializedName("songTitle")
	val songTitle: String? = null,

	@field:SerializedName("songLyrics")
	val songLyrics: String? = null,

	@field:SerializedName("artist")
	val artist: String? = null,

	@field:SerializedName("songLyricsArr")
	val songLyricsArr: List<String?>? = null
)
