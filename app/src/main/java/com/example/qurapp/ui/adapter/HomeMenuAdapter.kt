package com.example.qurapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qurapp.databinding.AyahItemBinding
import com.example.qurapp.databinding.HomeMenuItemBinding
import com.example.qurapp.ui.adapter.AyahAdapter.MyViewHolder
import com.example.qurapp.ui.model.HomeMenu

class HomeMenuAdapter : ListAdapter<HomeMenu, HomeMenuAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeMenuAdapter.MyViewHolder {
        val binding = HomeMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  HomeMenuAdapter.MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HomeMenuAdapter.MyViewHolder,
        posistion: Int
    ) {
        val menu = getItem(posistion)
        holder.bind(menu)
    }


    class MyViewHolder(val binding: HomeMenuItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (menu : HomeMenu) {
            binding.tvMenuName.text = menu.title
            binding.iconMenu.setImageResource(menu.icon)
        }
    }

    companion object {
        val DIFF_CALLBACK = object  : DiffUtil.ItemCallback<HomeMenu>() {
            override fun areItemsTheSame(
                oldItem: HomeMenu,
                newItem: HomeMenu
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HomeMenu,
                newItem: HomeMenu
            ): Boolean {
                return oldItem == newItem
            }


        }
    }

}