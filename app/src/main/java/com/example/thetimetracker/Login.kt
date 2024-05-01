package com.example.thetimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            //Initialize the firebase auth
        auth = FirebaseAuth.getInstance()

        val submitButton = findViewById<MaterialButton>(R.id.submitButton)
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)

        submitButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                //Authenticate User
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){
                            //Sign in success, navigate to home screen
                            val intent = Intent(this, DailyGoalEntries::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    baseContext, "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}