package com.example.thetimetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class EntriesMade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries_made)

        val backBtn = findViewById<LinearLayout>(R.id.btnBack1)

        backBtn.setOnClickListener {
            finish()
        }
    }
}