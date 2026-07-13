package com.example.qurapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurapp.R
import com.example.qurapp.databinding.ActivityDetailDoaBinding
import com.example.qurapp.ui.viewmodel.DetailDoaViewModel

class DetailDoaActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailDoaBinding
    private val viewModel : DetailDoaViewModel by viewModels()

    companion object {
        const val doaID = "doa_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emptyData()

        val id = intent.getIntExtra(doaID, -1)

        if (id == -1) {
            Toast.makeText(
                this,
                "Data Doa Tidak Ditemukan",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        viewModel.detailDoa(id)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.detailDoa.observe(this) {detailDoa ->
            showLoading(false)
            showData()
            binding.tvNamaDoa.text = detailDoa.nama
            binding.tvDoaArabic.text = detailDoa.ar
            binding.tvDoaLatin.text = detailDoa.tr
            binding.tvArtiDoa.text = detailDoa.idn
        }

//        viewModel.detailDoa(id)
//        val layoutManager = LinearLayoutManager(this)
//        binding.tvNamaDoa.

        Log.d("Detail Surah Activity", "ID Detail Doa = $id")


    }

    private fun emptyData() {
        binding.cardView3.visibility = View.GONE
        binding.tvArab.visibility = View.GONE
        binding.tvArti.visibility = View.GONE
        binding.materialCardView.visibility = View.GONE
        binding.cardViewDoaArabic.visibility = View.GONE
        binding.cardIconTerjemahan.visibility = View.GONE
        binding.cardViewTerjemahan.visibility = View.GONE
    }

    private fun showData() {
        binding.cardView3.visibility = View.VISIBLE
        binding.tvArab.visibility = View.VISIBLE
        binding.tvArti.visibility = View.VISIBLE
        binding.materialCardView.visibility = View.VISIBLE
        binding.cardViewDoaArabic.visibility = View.VISIBLE
        binding.cardIconTerjemahan.visibility = View.VISIBLE
        binding.cardViewTerjemahan.visibility = View.VISIBLE
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}