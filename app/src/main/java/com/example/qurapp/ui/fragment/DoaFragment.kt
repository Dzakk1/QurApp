package com.example.qurapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurapp.R
import com.example.qurapp.databinding.FragmentDoaBinding
import com.example.qurapp.response.DoaItem
import com.example.qurapp.ui.adapter.DoaAdapter
import com.example.qurapp.ui.viewmodel.DoaViewModel

class DoaFragment : Fragment() {

    private var _binding : FragmentDoaBinding ?= null
    private val binding get() = _binding!!

    private val doaViewmodel : DoaViewModel by activityViewModels ()
    private lateinit var adapter: DoaAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_doa, container, false)
        _binding = FragmentDoaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDoa.layoutManager = layoutManager

        adapter = DoaAdapter()
        binding.rvDoa.adapter = adapter



//        try - search


        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDoa.addItemDecoration(itemDecoration)

        doaViewmodel.doa.observe(viewLifecycleOwner) { doa ->
            setAllDoaData(doa)
        }

        doaViewmodel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

//        try search event

        binding.etSearch.doAfterTextChanged { text ->

            val searchKeyword = text.toString().trim()

            if (searchKeyword.isEmpty()) {
                doaViewmodel.findAllDoa()
            } else {
                doaViewmodel.searchDoaByGroup(searchKeyword)
            }
        }
//        end try
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAllDoaData(doa : List<DoaItem>) {
        adapter.submitList(doa)
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}