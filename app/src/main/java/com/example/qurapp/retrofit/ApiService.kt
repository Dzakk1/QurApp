package com.example.qurapp.retrofit

import com.example.qurapp.response.DetailSurahResponse
import com.example.qurapp.response.SurahResponse
import com.example.qurapp.response.TafsirResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("surat")
    fun getSurat() : Call<SurahResponse>

    @GET("surat/{id}")
    fun getDetailSurat(@Path("id") id : Int) : Call<DetailSurahResponse>

    @GET("tafsir/{id}")
    fun getSurahTafsir(@Path("id" ) id : Int) : Call<TafsirResponse>
}