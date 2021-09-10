package com.example.sundaymobilityassignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sundaymobilityassignment.adapters.CountryAdapter
import com.example.sundaymobilityassignment.databinding.FragmentHomeBinding
import com.example.sundaymobilityassignment.utils.jsonParse
import com.example.sundaymobilityassignment.utils.recyclerViewSetup

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = CountryAdapter()
        requireContext().recyclerViewSetup(binding.recycler, adapter)
        requireContext().jsonParse().observe(viewLifecycleOwner, { countries ->
         adapter.differ.submitList(countries)
        })

        return binding.root
    }
}

