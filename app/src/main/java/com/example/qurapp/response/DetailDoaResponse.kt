package com.example.qurapp.response

import com.google.gson.annotations.SerializedName

data class DetailDoaResponse(

	@field:SerializedName("data")
	val detailDoaData: DetailDoaData,

	@field:SerializedName("status")
	val status: String
)

data class DetailDoaData(

	@field:SerializedName("ar")
	val ar: String,

	@field:SerializedName("idn")
	val idn: String,

	@field:SerializedName("tentang")
	val tentang: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("grup")
	val grup: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("tag")
	val tag: List<String>,

	@field:SerializedName("tr")
	val tr: String
)
