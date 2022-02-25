package com.example.home_widget

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val intent = Intent(this, WidgetProvider::class.java)
        intent.action = WidgetProvider.ACTION_UPDATE
        sendBroadcast(intent)
        super.onBackPressed()
    }
}