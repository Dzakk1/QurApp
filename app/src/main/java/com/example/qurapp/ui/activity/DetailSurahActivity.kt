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
import com.example.qurapp.databinding.ActivityDetailSurahBinding
import com.example.qurapp.ui.adapter.AyahAdapter
import com.example.qurapp.ui.viewmodel.DetailSurahViewModel

class DetailSurahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSurahBinding
    private val viewModel: DetailSurahViewModel by viewModels()


    companion object {
        const val  surahID = "surah_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        get id
        val id = intent.getIntExtra(surahID, -1)
//        id?.let {
//            viewModel.detailSurah(it)
//        }

        if (id == -1) {
            Toast.makeText(
                this,
                "Data surah tidak ditemukan",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        viewModel.detailSurah(id)
        val layoutManager = LinearLayoutManager(this)
        binding.rvSurah.layoutManager = layoutManager

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }


        viewModel.isLoading.observe(this) {
            showLoading(it)
        }


        viewModel.detailSurah.observe(this) {detailSurah ->
            showLoading(false)

            binding.tvSurahName.text = detailSurah.namaLatin
            binding.tvArtiSurah.text = detailSurah.arti
            binding.tvTempatTurun.text = detailSurah.tempatTurun
            binding.tvJumlahAyah.text = detailSurah.jumlahAyat.toString() + " ayat"


            val adapter = AyahAdapter()

            adapter.submitList(
                detailSurah.ayat?.filterNotNull()
            )
            binding.rvSurah.adapter = adapter
        }

        Log.d("Detail Surah Actvity", "Id surah = $id")



    }


    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else  {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}