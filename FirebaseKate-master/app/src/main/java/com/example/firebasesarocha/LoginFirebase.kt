package com.example.firebasesarocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasesarocha.databinding.ActivityLoginFirebaseBinding

import com.google.firebase.auth.FirebaseAuth

class LoginFirebase : AppCompatActivity() {

    private lateinit var binding: ActivityLoginFirebaseBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.bttLogin.setOnClickListener {
            val username = binding.editTextTextPersonName2.text.toString()
            val password = binding.editTextTextPassword3.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Finish::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this,"Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding
    }
}