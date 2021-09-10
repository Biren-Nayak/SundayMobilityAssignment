package com.example.sundaymobilityassignment.ui.fragment

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sundaymobilityassignment.R
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
        val oldColors = binding.firstName.textColors


        binding.firstNameCard.setOnClickListener {
            val colors = updateButtons(it, binding.firstName)
            updateList(adapter, sortList(playerList))
            binding.lastName.setTextColor(oldColors)
            binding.lastNameCard.backgroundTintList = colors[1]
        }
        binding.lastNameCard.setOnClickListener {
            val colors = updateButtons(it, binding.lastName)
            updateList(adapter, sortList(playerList, false))
            binding.firstName.setTextColor(oldColors)
            binding.firstNameCard.backgroundTintList = colors[1]
        }

        return binding.root
    }

    private fun updateButtons(it: View, text: TextView): List<ColorStateList?> {
        val colors = buttonColor()
        text.setTextColor(ContextCompat.getColor(requireContext(), R.color.design_default_color_on_primary))
        it.backgroundTintList = colors[0]
        return colors
    }

    private fun buttonColor() = when (activity?.resources!!.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
        true -> {
            listOf<ColorStateList?>(
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.design_default_color_secondary)),
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.cardview_dark_background))
            )
        }
        else -> {
            listOf<ColorStateList?>(
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.sun)),
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),
                    R.color.cardview_light_background))
            )

        }
    }

    private fun updateList(
        adapter: PlayerAdapter,
        players: List<Player>,
    ) {
        adapter.differ.submitList(players)
    }


}

