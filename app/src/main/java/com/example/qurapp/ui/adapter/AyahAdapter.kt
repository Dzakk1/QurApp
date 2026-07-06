package com.example.qurapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qurapp.databinding.AyahItemBinding
import com.example.qurapp.response.AyatItem
import com.example.qurapp.ui.adapter.AyahAdapter.MyViewHolder
import androidx.recyclerview.widget.DiffUtil
class AyahAdapter  : ListAdapter<AyatItem, MyViewHolder> (DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = AyahItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        posistion: Int
    ) {
        val ayah = getItem(posistion)
        holder.bind(ayah)
    }


    class MyViewHolder(val binding: AyahItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ayah : AyatItem) {
            binding.numAyah.text = ayah.nomorAyat.toString()
            binding.teksArabic.text = ayah.teksArab
            binding.teksLatin.text = ayah.teksIndonesia

        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AyatItem>() {
            override fun areItemsTheSame(
                oldItem: AyatItem,
                newItem: AyatItem
            ): Boolean {
                return  oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AyatItem,
                newItem: AyatItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}