package com.example.sundaymobilityassignment.utils

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sundaymobilityassignment.adapters.CountryAdapter

fun Context.jsonParse() : MutableLiveData<List<String>> {
    val countries= MutableLiveData<List<String>>()
    val requestQueue= Volley.newRequestQueue(this)
    val request = JsonObjectRequest(Request.Method.GET, Constants.JSONUrl, null, { response ->
        countries.value = response.keys().asSequence().toList()
    },  { error -> error.printStackTrace() })
    requestQueue.add(request)
    return countries
}

fun Context.recyclerViewSetup(recyclerView: RecyclerView, newAdapter: CountryAdapter){
    recyclerView.apply {
        this.adapter = newAdapter
        layoutManager = LinearLayoutManager(this@recyclerViewSetup)
    }
}