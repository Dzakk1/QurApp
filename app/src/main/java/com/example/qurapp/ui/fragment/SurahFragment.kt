package com.example.qurapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurapp.R
import com.example.qurapp.databinding.FragmentSurahBinding
import com.example.qurapp.response.DataItem
import com.example.qurapp.ui.adapter.SurahAdapter
import com.example.qurapp.ui.viewmodel.SurahViewModel
import kotlinx.coroutines.processNextEventInCurrentThread


class SurahFragment : Fragment() {

    private var _binding : FragmentSurahBinding ?= null
    private val binding get() = _binding!!


    private val surahViewModel : SurahViewModel by activityViewModels()
    private lateinit var adapter: SurahAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_surah, container, false)

        _binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvSurah.layoutManager = layoutManager

        adapter = SurahAdapter()
        binding.rvSurah.adapter = adapter

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvSurah.addItemDecoration(itemDecoration)


        surahViewModel.surah.observe(viewLifecycleOwner) { surah ->
            setSurahData(surah)
        }

        surahViewModel.isLoading.observe(viewLifecycleOwner) {
            showloading(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setSurahData(surah : List<DataItem>) {
        adapter.submitList(surah)
    }
    private fun showloading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
//    companion object {
//
//    }
}