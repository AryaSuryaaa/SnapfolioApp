package com.aryasurya.snapfolioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.aryasurya.snapfolioapp.databinding.ActivityRegistBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    companion object {
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = findViewById(R.id.progressBar)


        with(binding) {
            btnRegist.setOnClickListener {
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                createAccount(email, password)
            }
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()){
            return
        }

        progressBar.visibility = View.VISIBLE

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    var errorMessage = "Authentication failed."

                    if ( task.exception is FirebaseAuthUserCollisionException) {
                        errorMessage = "Email already exists. Please use a different email."
                    }

                    Toast.makeText(
                        baseContext,
                        errorMessage,
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                progressBar.visibility = View.INVISIBLE
            }
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                Toast.makeText(applicationContext, "Reload successful!", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(applicationContext, "Failed to reload user.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.email.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.layoutEmail.error = "Required!"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.layoutEmail.error = "Invalid email address!"
        }
        else {
            binding.email.error = null
        }

        val password = binding.password.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.layoutPassword.error = "Required!"
            valid = false
        } else {
            binding.password.error = null
        }

        return valid
    }

}