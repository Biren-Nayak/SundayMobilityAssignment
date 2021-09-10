package com.example.sundaymobilityassignment.ui.fragment

import android.graphics.Color
import android.graphics.Color.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.fragment.app.Fragment
import com.example.sundaymobilityassignment.utils.Constants.sortList
import com.example.sundaymobilityassignment.databinding.FragmentPlayersBinding
import com.example.sundaymobilityassignment.classes.Player
import com.example.sundaymobilityassignment.adapters.PlayerAdapter
import com.example.sundaymobilityassignment.utils.jsonParse
import com.example.sundaymobilityassignment.utils.recyclerViewSetup

class PlayersFragment: Fragment() {
    private lateinit var binding: FragmentPlayersBinding
    private lateinit var playerList: List<Player>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)
        val countryName = PlayersFragmentArgs.fromBundle(requireArguments()).countryName
        val adapter = PlayerAdapter()
        requireContext().recyclerViewSetup(binding.recyclerPlayers, adapter)

        requireContext().jsonParse(countryName).observe(viewLifecycleOwner,{ players ->
            playerList = players
            updateList(adapter, players)
        })


        binding.firstNameCard.setOnClickListener {
            updateList(adapter, sortList(playerList))
            binding.firstNameCard.setCardBackgroundColor(DKGRAY)
            binding.lastNameCard.setCardBackgroundColor(TRANSPARENT)
        }
        binding.lastNameCard.setOnClickListener {
            updateList(adapter, sortList(playerList, false))
            binding.lastNameCard.setCardBackgroundColor(DKGRAY)
            binding.firstNameCard.setCardBackgroundColor(TRANSPARENT)
        }
        return binding.root
    }

    private fun updateList(
        adapter: PlayerAdapter,
        players: List<Player>,
    ) {
        adapter.differ.submitList(players)
    }


}

