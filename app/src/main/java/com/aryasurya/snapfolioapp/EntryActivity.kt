package com.aryasurya.snapfolioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.aryasurya.snapfolioapp.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener(this)

        val btnRegist = findViewById<Button>(R.id.btn_regist)
        btnRegist.setOnClickListener(this)
    }

    override  fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {

            }
            R.id.btn_regist -> {
                val intent = Intent(this@EntryActivity, RegistActivity::class.java)
                startActivity(intent)
            }
        }
    }
}