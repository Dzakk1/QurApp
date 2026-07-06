package com.example.qurapp.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qurapp.databinding.SurahItemBinding
import com.example.qurapp.response.DataItem
import com.example.qurapp.ui.activity.DetailSurahActivity
import com.example.qurapp.ui.viewmodel.DetailSurahViewModel

class SurahAdapter : ListAdapter<DataItem, SurahAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SurahAdapter.MyViewHolder {
        val binding = SurahItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahAdapter.MyViewHolder, position: Int) {
        val surah = getItem(position)
        holder.bind(surah)

//        click event
        holder.itemView.setOnClickListener {

//            log id
            Log.d("Surah Adapter Clicked", "idSurah = ${surah.nomor}")

            Log.d(
                "SURAH_CLICK",
                "type = ${surah.nomor?.javaClass}"
            )
            val intent = Intent(holder.itemView.context, DetailSurahActivity::class.java)
            intent.putExtra(DetailSurahActivity.surahID, surah.nomor)
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(val binding : SurahItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surah : DataItem) {
            binding.tvNomorSurah.text = surah.nomor.toString()
            binding.tvNamaLatinSurah.text = surah.namaLatin
            binding.tvArtiSurah.text = surah.arti
            binding.tvJumlahAyat.text = surah.jumlahAyat.toString() + " ayat"
            binding.tvNamaSurahHijaiyah.text = surah.nama
        }
    }


    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}