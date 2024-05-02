package com.example.thetimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class EntriesMade : AppCompatActivity(), TaskAdapter.OnEditClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entries_made)

        recyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskAdapter(this)
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadTasks()

        val backButton = findViewById<ImageView>(R.id.backButton)

        backButton.setOnClickListener{
            val intent = Intent(this, AddEntry::class.java)
            startActivity(intent)
            finish()
        }
        taskAdapter.setOnEditClickListener(this)
    }

    override fun onEditClick(task: Task) {
        editTask(task)
    }

    private fun editTask(task: Task) {
        val intent = Intent(this, AddEntry::class.java)
        intent.putExtra("task", task)
        startActivity(intent)
    }
    private fun loadTasks() {
        val databaseHelper = DatabaseHelper(this)
        val tasks = databaseHelper.readAllTasks()
        taskAdapter.updateTasks(tasks)
    }

}