package com.example.qurapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qurapp.response.Data
import com.example.qurapp.response.DataItem
import com.example.qurapp.response.SurahResponse
import com.example.qurapp.response.TafsirData
import com.example.qurapp.response.TafsirItem
import com.example.qurapp.response.TafsirResponse
import com.example.qurapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TafsirViewModel : ViewModel(){

    private  val _surah = MutableLiveData<List<DataItem>>()
    val surah : LiveData<List<DataItem>> = _surah

//    tafsir list
//    private val _tafsir = MutableLiveData<List<TafsirItem>>()
//    val tafsir: LiveData<List<TafsirItem>> = _tafsir

    private val _tafsir = MutableLiveData<TafsirData>()
    val tafsir: LiveData<TafsirData> = _tafsir

    companion object {
        private val TAG = "TafsirViewModel"
    }

    init {
        if (_surah.value == null) {
            findSurah()
        }
    }

    private fun findSurah() {
        val client = ApiConfig.getApiSevice().getSurat()
        client.enqueue(object  : Callback<SurahResponse> {
            override fun onResponse(
                call: Call<SurahResponse?>,
                response: Response<SurahResponse>
            ) {
                if (response.isSuccessful) {
                    _surah.value = response.body()?.data
                }
            }

            override fun onFailure(
                call: Call<SurahResponse>,
                t: Throwable
            ) {
                Log.e(TAG, "Gagal: ${t.message.toString()}")
            }
        })
    }

//    function tafsir
fun findTafsir(id : Int) {

    Log.d(TAG, "Request Tafsir ID = $id")
    val client = ApiConfig.getApiSevice().getSurahTafsir(id)
    client.enqueue(object  : Callback<TafsirResponse> {
        override fun onResponse(
            call: Call<TafsirResponse?>,
            response: Response<TafsirResponse?>
        ) {
            if (response.isSuccessful) {
//                _tafsir.value = response.body()?.data?.tafsir
                _tafsir.value = response.body()?.data
            }

        }

        override fun onFailure(
            call: Call<TafsirResponse?>,
            t: Throwable
        ) {
            Log.e(TAG, "Tafsir Failed : ${t.message.toString()}")
        }

    })
}

}