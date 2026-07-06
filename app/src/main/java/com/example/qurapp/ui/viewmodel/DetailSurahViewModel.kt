package com.example.qurapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qurapp.response.Data
import com.example.qurapp.response.DetailSurahResponse
import com.example.qurapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSurahViewModel : ViewModel() {

    private val _detailSurah = MutableLiveData<Data>()
    val detailSurah: LiveData<Data> = _detailSurah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private val TAG = " DetailSurahViewModel"
        const val surahID = "surah_id"
    }

    fun detailSurah(id : Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiSevice().getDetailSurat(id)
        client.enqueue(object  : Callback<DetailSurahResponse> {
            override fun onResponse(
                call: Call<DetailSurahResponse>,
                response: Response<DetailSurahResponse?>
            ) {
                _isLoading.value = false
                response.body()?.data?.let {
                    _detailSurah.value = it
                }
            }

            override fun onFailure(
                call: Call<DetailSurahResponse>,
                t: Throwable
            ) {
                Log.e(TAG, "Gagal: ${t.message.toString()}")
            }

        })
    }

}