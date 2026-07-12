package com.example.qurapp.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qurapp.databinding.DoaItemBinding
import com.example.qurapp.response.DoaItem
import com.example.qurapp.ui.activity.DetailDoaActivity

class DoaAdapter : ListAdapter<DoaItem, DoaAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = DoaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val doa = getItem(position)
        holder.bind(doa)


//        clicked event
        holder.itemView.setOnClickListener {
            Log.d("Doa Adapter", "id = ${doa.id} clicked")

            val intent = Intent(holder.itemView.context, DetailDoaActivity::class.java)
            intent.putExtra(DetailDoaActivity.doaID, doa.id)
            holder.itemView.context.startActivity(intent)
        }


    }

    class MyViewHolder(val binding: DoaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(doa : DoaItem) {
            binding.tvNamaDoa.text = doa.nama
            binding.tvGroupDoa.text = doa.grup
        }
    }

    companion object {
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<DoaItem>() {
            override fun areItemsTheSame(
                oldItem: DoaItem,
                newItem: DoaItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DoaItem,
                newItem: DoaItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}