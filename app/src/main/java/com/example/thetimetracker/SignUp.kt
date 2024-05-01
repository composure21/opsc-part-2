package com.example.thetimetracker

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signUpButton = findViewById<MaterialButton>(R.id.submitButton2)
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<TextInputEditText>(R.id.c_passwordEditText)

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if(password == confirmPassword){
                    //Create User with email and password
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){
                            task -> if(task.isSuccessful){
                                //Sign-Up Successful, navigate to activity_login
                                Toast.makeText(
                                    this,
                                    "Sign Up Successful!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            val intent = Intent(this,Login::class.java)
                            startActivity(intent)
                            finish()
                        }
                            else{
                                //Sign-up failed, display message
                            val errorMessage = "Sign up Failed: ${task.exception?.message}"
                            Toast.makeText(
                                this,
                                errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                            // Log the exception
                            Log.e(TAG, errorMessage, task.exception)
                        }
                    }
                }
                else {
                    // Passwords do not match
                    Toast.makeText(
                        this,
                        "Passwords do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                // Email, password, or confirm password is empty
                Toast.makeText(
                    this,
                    "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}