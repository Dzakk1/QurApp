package com.example.qurapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurapp.R
import com.example.qurapp.databinding.FragmentTafsirBinding
import com.example.qurapp.response.Data
import com.example.qurapp.response.DataItem
import com.example.qurapp.response.TafsirData
import com.example.qurapp.response.TafsirItem
import com.example.qurapp.ui.adapter.TafsirAdapter
import com.example.qurapp.ui.viewmodel.TafsirViewModel
import kotlin.math.log
import kotlin.toString


class TafsirFragment : Fragment() {


    private var _binding : FragmentTafsirBinding ?= null
    private val binding get() = _binding!!

    private val tafsirViewModel : TafsirViewModel by activityViewModels()

    private var surahList: List<DataItem> = emptyList()

//    adapter
    private lateinit var adapter: TafsirAdapter

//    try selected surah tafsir
    companion object {
        const val surahTafsirId = "tafsir_surah_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTafsirBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showEmptyTafsir()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvSurahTafsir.layoutManager = layoutManager

        adapter = TafsirAdapter()
        binding.rvSurahTafsir.adapter = adapter

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvSurahTafsir.addItemDecoration(itemDecoration)


//        viewmodel
        tafsirViewModel.tafsir.observe(viewLifecycleOwner) { tafsirData ->
//            setTafsirData(tafsirItem)
            showTafsir()

            binding.tvSurahName.text = tafsirData.namaLatin
            binding.tvArtiSurah.text = tafsirData.arti
            binding.tvJumlahAyahTafsir.text = "${tafsirData.jumlahAyat} Ayat"

            adapter.submitList(tafsirData.tafsir)
        }

//        surahDropDown()
        tafsirViewModel.surah.observe(viewLifecycleOwner) { list ->
            surahList = list

            val dropdownList = list.map {
                "${it.nomor}. ${it.namaLatin}"
            }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                dropdownList
            )

            binding.actvSurah.setAdapter(adapter)

            binding.actvSurah.setOnItemClickListener { parent, _, position, _ ->
//                val selectedSurah = parent.getItemAtPosition(position).toString()

                val selectedSurah = surahList[position]

                Log.d("Tafsir Fragment", "Nomor : ${selectedSurah.nomor}, Nama : ${selectedSurah.namaLatin}")

                tafsirViewModel.findTafsir(selectedSurah.nomor)

            }


        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showEmptyTafsir() {
        binding.tvAlertChoiceSurah.visibility = View.VISIBLE
        binding.cardTafsirData.visibility = View.GONE
    }

    private fun showTafsir() {
        binding.tvAlertChoiceSurah.visibility = View.GONE
        binding.cardTafsirData.visibility = View.VISIBLE
    }


}