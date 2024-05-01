package com.example.thetimetracker

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.util.*

class AddEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        val SubmitButton = findViewById<MaterialButton>(R.id.submitButton)

        SubmitButton.setOnClickListener{
            val taskName = findViewById<TextInputEditText>(R.id.taskNameEditText).text.toString();
            val description = findViewById<TextInputEditText>(R.id.descriptionEditText).text.toString()

            val startTimeButton = findViewById<Button>(R.id.btnStartTime)
            val endTimeButton = findViewById<Button>(R.id.btnEndTime)
            val DateButton = findViewById<Button>(R.id.btnDate)
            val category = findViewById<TextInputEditText>(R.id.categorytextbox)

            //Set OnClickListener for the starttime button
            startTimeButton.setOnClickListener{
                showDatePickerDialog(startTimeButton)
            }
            //Set OnClickListener for the endtime button
            endTimeButton.setOnClickListener{
                showDatePickerDialog(endTimeButton)
            }

            //Set OnClickListener for the endtime button
            DateButton.setOnClickListener{
                showDatePickerDialog(DateButton)
            }

            val intent = Intent(this, EntriesMade::class.java).apply{
                putExtra("TASK_NAME", taskName)
                putExtra("DESCRIPTION",description)
                putExtra("START_TIME",startTimeButton.text.toString())
                putExtra("END_TIME",endTimeButton.text.toString())
                putExtra("DATE",DateButton.text.toString())
                putExtra("CATEGORY",category.text.toString())
            }

        }

    }
    // Function to show the date picker dialog
    private fun showDatePickerDialog(button: Button) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Set the selected date to the button text
                val formattedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                button.text = formattedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
}
}