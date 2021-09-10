package com.example.sundaymobilityassignment.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sundaymobilityassignment.adapters.PlayerAdapter
import com.example.sundaymobilityassignment.classes.Player


fun Context.jsonParse(country: String): MutableLiveData<List<Player>> {
    val playerListLive = MutableLiveData<List<Player>>()
    val playerList = ArrayList<Player>()
    val requestQueue = Volley.newRequestQueue(this)
    val request = JsonObjectRequest(Request.Method.GET, Constants.JSONUrl, null, { response ->
        val players = response.getJSONArray(country)

        repeat(players.length()){ playerIndex ->
            val name = players.getJSONObject(playerIndex).get("name").toString().trim(',').trim('.').replaceFirst("-", " ")
            val flag = try {
                players.getJSONObject(playerIndex).getBoolean("captain")
            }catch (e: Exception){
                Toast.makeText(this, "Cannot fetch the captain value of $name", Toast.LENGTH_LONG).show()
                false
            }
            val nameList: List<String> =
                when(name.contains(" ")){
                    true -> name.split(" ")
                    false ->  listOf(name," ")
                }
            playerList.add(Player(nameList[0], nameList[1], flag))
            playerListLive.value = playerList
        }


    },  { error -> error.printStackTrace() })
    requestQueue.add(request)

    return playerListLive
}

fun Context.recyclerViewSetup(recyclerView: RecyclerView, newAdapter: PlayerAdapter) {
    recyclerView.apply {
        adapter = newAdapter
        layoutManager = LinearLayoutManager(this@recyclerViewSetup)
    }
}