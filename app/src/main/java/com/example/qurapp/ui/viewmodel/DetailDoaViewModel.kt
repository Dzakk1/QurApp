package com.example.qurapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qurapp.response.DetailDoaData
import com.example.qurapp.response.DetailDoaResponse
import com.example.qurapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDoaViewModel : ViewModel() {

    private val _detailDoa = MutableLiveData<DetailDoaData>()
    val detailDoa : LiveData<DetailDoaData> = _detailDoa

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private val TAG = "DetailDoaViewModel"
        const val doaID = "doa_id"
    }

    fun detailDoa(id: Int) {
        _isLoading.value = true

        val client = ApiConfig.getApiSevice().getDetailDoa(id)
        client.enqueue(object  : Callback<DetailDoaResponse> {
            override fun onResponse(
                call: Call<DetailDoaResponse?>,
                response: Response<DetailDoaResponse?>
            ) {
                _isLoading.value = false
                response.body()?.detailDoaData.let {
                    _detailDoa.value = it
                }
            }

            override fun onFailure(
                call: Call<DetailDoaResponse?>,
                t: Throwable
            ) {
                Log.e(TAG, "Gagal : ${t.message.toString()}")
            }

        })
    }

}










