package com.example.thetimetracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView
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
    private lateinit var drawerLayout: DrawerLayout
    private  lateinit var navView: NavigationView


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

        drawerLayout = findViewById(R.id.menu_drawer)
        navView = findViewById(R.id.navigation_view)

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_add -> {
                    val AddTasks = Intent(this, AddEntry::class.java)
                    startActivity(AddTasks)
                    Log.d("Navigation", "Clicked on Add Entry")
                }
                R.id.nav_tasks -> {
                    val EntriesMadeIntent = Intent(this, EntriesMade::class.java)
                    startActivity(EntriesMadeIntent)
                    Log.d("Navigation", "Clicked on Entries Made")
                }
                else -> Log.d("Navigation", "Unknown menu item clicked")
            }
            true
        }
    }

            override fun onBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    super.onBackPressed()
                }
            }

}