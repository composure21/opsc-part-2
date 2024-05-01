package com.example.thetimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

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

        var textView2 = findViewById<TextView>(R.id.textView2)
        var textView3 = findViewById<TextView>(R.id.textView3)
        var textView4 = findViewById<TextView>(R.id.textView4)
        var textView5 = findViewById<TextView>(R.id.textView5)
        var textView6 = findViewById<TextView>(R.id.textView6)
//        var textview7 = findViewById<>()
        val backButton = findViewById<ImageView>(R.id.backButton)

//        val textView7 = findViewById<TextInputEditText>(R.id.textView7).text.toString()

        textView2.text = "Task Name: $taskName"
        textView3.text = "Description: $description"
        textView4.text = "Start Time: $startTime"
        textView5.text = "End Time: $endTime"
        textView6.text = "Date: $date"
//        textview7.text = "Category: $category"


        backButton.setOnClickListener{
            val intent = Intent(this, AddEntry::class.java)
            startActivity(intent)
        }


    }
}