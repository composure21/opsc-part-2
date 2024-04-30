package com.example.thetimetracker

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //UI Views


    //CAMERA PERMISSIONS
    private val CAMERA_REQUEST_CODE: Int = 100
    private val STORAGE_REQUEST_CODE = 101


    //Objects
//    private var cameraPermissions: Array<String?>?
//    private val storagePermissions: Array<String>

    //Uri of the image that we will take from Camera/Gallery
    private val imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}