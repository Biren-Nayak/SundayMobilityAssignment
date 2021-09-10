package com.example.sundaymobilityassignment.utils

import com.example.sundaymobilityassignment.classes.Player


object Constants {
    const val JSONUrl = "https://test.oye.direct/players"

    fun sortList(players: List<Player>, isByFirstName: Boolean = true): List<Player> {
        return when (isByFirstName) {
            true -> players.sortedBy {
                it.firstName
            }
            false -> players.sortedBy {
                it.lastName
            }
        }
    }

}





