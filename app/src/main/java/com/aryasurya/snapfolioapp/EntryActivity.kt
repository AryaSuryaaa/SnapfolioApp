package com.aryasurya.snapfolioapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.aryasurya.snapfolioapp.databinding.ActivityEntryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EntryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEntryBinding
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "EntryActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener(this)

        val btnRegist = findViewById<Button>(R.id.btn_regist)
        btnRegist.setOnClickListener(this)
    }

    override  fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_login -> {
                val email = binding.inputEmail.text.toString()
                val password = binding.inputPassword.text.toString()
                signInWithEmailAndPassword(email, password)
            }
            R.id.btn_regist -> {
                val intent = Intent(this@EntryActivity, RegistActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
            if (task.isSuccessful) {
                val intentToMainActivity = Intent(this@EntryActivity, MainActivity::class.java)
                startActivity(intentToMainActivity)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)

                var erorMessage = "Incorrect email address or password"

                if (task.exception is FirebaseAuthInvalidUserException) {
                    erorMessage = "Email address is not registered"
                }
                Toast.makeText(
                    baseContext,
                    erorMessage,
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

}