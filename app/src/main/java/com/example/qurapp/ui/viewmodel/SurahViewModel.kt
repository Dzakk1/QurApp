package com.example.qurapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qurapp.response.DataItem
import com.example.qurapp.response.SurahResponse
import com.example.qurapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahViewModel : ViewModel() {
    private val _surah = MutableLiveData<List<DataItem>>()
    val surah : LiveData<List<DataItem>> = _surah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private val TAG = "SurahViewModel"
    }
    
    init {
        if (_surah.value == null) {
            findSurah()
        }
    }
    
    private fun  findSurah() {
        _isLoading.value = true
        val client = ApiConfig.getApiSevice().getSurat()
        client.enqueue(object : Callback<SurahResponse> {
            override fun onResponse(
                call: Call<SurahResponse?>,
                response: Response<SurahResponse?>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _surah.value = response.body()?.data
                }
            }

            override fun onFailure(
                call: Call<SurahResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Gagal: ${t.message.toString()}")
            }
        })
    }
}