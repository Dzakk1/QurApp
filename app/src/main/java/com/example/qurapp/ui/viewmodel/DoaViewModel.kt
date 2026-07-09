package com.example.qurapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qurapp.response.DoaItem
import com.example.qurapp.response.DoaResponse
import com.example.qurapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaViewModel : ViewModel() {

    private val _doa = MutableLiveData<List<DoaItem>>()
    val doa : LiveData<List<DoaItem>> = _doa

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private val TAG = "DoaViewModel"
    }

    init {
        if (_doa.value ==null) {
            findAllDoa()
        }
    }

    private fun findAllDoa() {
        _isLoading.value = true
        val client = ApiConfig.getApiSevice().getAllDoa()
        client.enqueue(object  : Callback<DoaResponse> {
            override fun onResponse(
                call: Call<DoaResponse?>,
                response: Response<DoaResponse?>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _doa.value = response.body()?.data
                }
            }

            override fun onFailure(
                call: Call<DoaResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Gagal : ${t.message.toString()}")
            }

        })
    }
}