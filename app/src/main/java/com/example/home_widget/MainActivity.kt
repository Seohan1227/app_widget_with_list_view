package com.example.home_widget

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.home_widget.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val getExtra = intent.getStringExtra(MyRemoteViewsFactory.EXTRA_FROM_APP_WIDGET)
        val text = if (getExtra != null) "선택된 포지션은 ${getExtra}입니다."
                   else "선택된 포지션이 없습니다."
        binding.textViewMain.text = text
    }
}