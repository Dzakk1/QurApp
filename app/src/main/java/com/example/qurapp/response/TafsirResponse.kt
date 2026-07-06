package com.example.qurapp.response

import com.google.gson.annotations.SerializedName

data class TafsirResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: TafsirData,

	@field:SerializedName("message")
	val message: String
)

data class TafsirItem(

	@field:SerializedName("ayat")
	val ayat: Int,

	@field:SerializedName("teks")
	val teks: String
)


data class TafsirData(

	@SerializedName("nomor")
	val nomor: Int,

	@SerializedName("nama")
	val nama: String,

	@SerializedName("namaLatin")
	val namaLatin: String,

	@SerializedName("jumlahAyat")
	val jumlahAyat: Int,

	@SerializedName("arti")
	val arti: String,

	@SerializedName("deskripsi")
	val deskripsi: String,

	@SerializedName("audioFull")
	val audioFull: AudioFull,

	@SerializedName("tafsir")
	val tafsir: List<TafsirItem>
)