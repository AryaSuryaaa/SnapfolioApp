package com.aryasurya.snapfolioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aryasurya.snapfolioapp.databinding.ActivityRegistBinding

class RegistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnRegist = findViewById<Button>(R.id.btn_regist)

    }
}