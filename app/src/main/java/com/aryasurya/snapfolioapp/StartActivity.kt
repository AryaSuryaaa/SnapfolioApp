package com.aryasurya.snapfolioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btnStart = findViewById<Button>(R.id.btn_start);

        btnStart.setOnClickListener{
            val toLogin = Intent(this@StartActivity, EntryActivity::class.java)
            startActivity(toLogin)
        }
    }
}