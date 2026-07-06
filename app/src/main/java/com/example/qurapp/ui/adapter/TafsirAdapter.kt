package com.example.qurapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qurapp.databinding.TafsirItemBinding
import com.example.qurapp.response.TafsirData
import com.example.qurapp.response.TafsirItem

class TafsirAdapter : ListAdapter<TafsirItem, TafsirAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: TafsirItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tafsir : TafsirItem) {
            binding.tvTitleNumAyah.text = "Ayat" + tafsir.ayat.toString()
            binding.tvTafsir.text = tafsir.teks.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TafsirAdapter.MyViewHolder {
        val binding = TafsirItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TafsirAdapter.MyViewHolder, position: Int) {
        val tafsir = getItem(position)
        holder.bind(tafsir)


    }

    companion object {
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<TafsirItem>() {
            override fun areItemsTheSame(
                oldItem: TafsirItem,
                newItem: TafsirItem
            ): Boolean {
                return  oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TafsirItem,
                newItem: TafsirItem
            ): Boolean {
                return  oldItem == newItem
            }


        }
    }
}