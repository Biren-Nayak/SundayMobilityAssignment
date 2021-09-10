package com.example.sundaymobilityassignment.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.sundaymobilityassignment.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.custom_action_bar)
        supportActionBar!!.hideOffset = supportActionBar!!.height
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