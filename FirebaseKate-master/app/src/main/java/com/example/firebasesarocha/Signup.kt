package com.example.firebasesarocha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasesarocha.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.bttSingup.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val cpPassword = binding.cpassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty() && cpPassword.isNotEmpty()){
                if(password == cpPassword){
                    firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,LoginFirebase::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this,"Password not Match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}