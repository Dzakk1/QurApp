package com.example.qurapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurapp.R
import com.example.qurapp.databinding.FragmentHomeBinding
import com.example.qurapp.ui.adapter.HomeMenuAdapter
import com.example.qurapp.ui.adapter.SurahAdapter
import com.example.qurapp.ui.model.HomeMenu
import com.example.qurapp.ui.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding ?= null
    private  val binding get() = _binding!!



//    viewmodel
    private  val homeViewModel : HomeViewModel by activityViewModels()

//    adapter
//    private lateinit var adapter: SurahAdapter

    private lateinit var adapter: HomeMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvHomeMenu.layoutManager = layoutManager

        adapter = HomeMenuAdapter()
        binding.rvHomeMenu.adapter = adapter

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvHomeMenu.addItemDecoration(itemDecoration)


        adapter.submitList(
            listOf(
                HomeMenu(
                    "Quran",
                    R.drawable.import_contacts_20px,
                    R.drawable.menu_bg_green
                ),
                HomeMenu(
                    "Tafsir",
                    R.drawable.auto_stories_20px,
                    R.drawable.menu_bg_beige
                ),
                HomeMenu(
                    "Dua",
                    R.drawable.folded_hands_20px,
                    R.drawable.menu_bg_purple
                ),
                HomeMenu(
                    "Shalah Time",
                    R.drawable.prayer_times_20px,
                    R.drawable.menu_bg_pink
                ),
                HomeMenu(
                    "Qibla",
                    R.drawable.explore_20px,
                    R.drawable.menu_bg_pink
                ),
                HomeMenu(
                    "Bookmark",
                    R.drawable.bookmark_20px,
                    R.drawable.menu_bg_pink
                )

            )
        )





    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}