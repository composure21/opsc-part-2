package com.example.thetimetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText

class EntriesMade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries_made)

        //Retrieve the values passed from AddEntry activity
        val taskName = intent.getStringExtra("TASK_NAME")
        val description = intent.getStringExtra("DESCRIPTION")
        val startTime  = intent.getStringExtra("START_TIME")
        val endTime  = intent.getStringExtra("END_TIME")
        val date  = intent.getStringExtra("DATE")

//        val category  = intent.getStringExtra("CATEGORY")

        var textView2 = findViewById<TextInputEditText>(R.id.textView2).text.toString()
        var textView3 = findViewById<TextInputEditText>(R.id.textView3).text.toString()
        var textView4 = findViewById<TextInputEditText>(R.id.textView4).text.toString()
        var textView5 = findViewById<TextInputEditText>(R.id.textView5).text.toString()
        var textView6 = findViewById<TextInputEditText>(R.id.textView6).text.toString()

//        val textView7 = findViewById<TextInputEditText>(R.id.textView7).text.toString()

        textView2 = "Task Name: $taskName"
        textView3 = "Description: $description"
        textView4 = "Start Time: $startTime"
        textView5 = "End Time: $endTime"
        textView6 = "Date: $date"
//        textview7 = "Category: $category"



    }
}