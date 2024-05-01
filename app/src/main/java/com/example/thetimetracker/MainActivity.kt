package com.example.thetimetracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //UI Views


    //CAMERA PERMISSIONS
    private val CAMERA_REQUEST_CODE: Int = 100
    private val STORAGE_REQUEST_CODE = 101


    //Objects
//    private var cameraPermissions: Array<String?>?
//    private val storagePermissions: Array<String>
    private  lateinit var auth: FirebaseAuth

    //Uri of the image that we will take from Camera/Gallery
    private val imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupButton = findViewById<MaterialButton>(R.id.signup_button)
        val loginButton = findViewById<MaterialButton>(R.id.login_button)

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        signupButton.setOnClickListener{
            //Navigate to SignupActivity
            val signUpIntent = Intent(this, SignUp::class.java)
            startActivity(signUpIntent)
        }

        loginButton.setOnClickListener{
            //Navigate to LoginActivity
            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
        }
    }
}