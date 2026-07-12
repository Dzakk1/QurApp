package com.example.qurapp.retrofit

import com.example.qurapp.response.DetailDoaResponse
import com.example.qurapp.response.DetailSurahResponse
import com.example.qurapp.response.DoaResponse
import com.example.qurapp.response.SurahResponse
import com.example.qurapp.response.TafsirResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/surat")
    fun getSurat() : Call<SurahResponse>

    @GET("v2/surat/{id}")
    fun getDetailSurat(@Path("id") id : Int) : Call<DetailSurahResponse>

    @GET("v2/tafsir/{id}")
    fun getSurahTafsir(@Path("id" ) id : Int) : Call<TafsirResponse>

    @GET("doa")
    fun getAllDoa(
        @Query("grup") grup : String? = null,
        @Query("tag") tag : String? = null
    ) : Call<DoaResponse>

    @GET("doa/{id}")
    fun getDetailDoa(
        @Path("id") id : Int) : Call<DetailDoaResponse>

}