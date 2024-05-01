package com.example.thetimetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class DailyGoalEntries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_goal_entries)

        val backBtn2 = findViewById<LinearLayout>(R.id.btnBack2)

        backBtn2.setOnClickListener {
            finish()
        }
    }
}