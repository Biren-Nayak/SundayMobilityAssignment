package com.example.sundaymobilityassignment.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.example.sundaymobilityassignment.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        //supportActionBar!!.setCustomView(R.layout.custom_action_bar)
    }

    fun toggleDarkMode(view: View) {
        AppCompatDelegate.setDefaultNightMode(
        when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            else -> {
                AppCompatDelegate.MODE_NIGHT_YES
            }
        })
        overridePendingTransition(0, 0)
    }

    fun onBackPressed(view: View){
        onBackPressed()
    }
}