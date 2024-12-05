package com.thaianramalho.trabalho3etapa

import android.content.ContentValues
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivitySelectionActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var selectedDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        dbHelper = DatabaseHelper(this)
        selectedDate = intent.getStringExtra("selectedDate") ?: ""

        val sportSpinner = findViewById<Spinner>(R.id.sportSpinner)
        val bookButton = findViewById<Button>(R.id.bookButton)

        // Populate the spinner with sports from the database
        populateSportSpinner(sportSpinner)

        bookButton.setOnClickListener {
            val selectedSport = sportSpinner.selectedItem.toString()
            if (selectedSport.isNotEmpty()) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put("date", selectedDate)
                    put("sport", selectedSport)
                }
                val newRowId = db.insert("bookings", null, values)
                if (newRowId != -1L) {
                    Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show()
                    finish() // Go back to the previous screen
                } else {
                    Toast.makeText(this, "Failed to book", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select a sport", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateSportSpinner(spinner: Spinner) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "sports",
            arrayOf("name"),
            null,
            null,
            null,
            null,
            null
        )
        val sports = mutableListOf<String>()
        while (cursor.moveToNext()) {
            sports.add(cursor.getString(cursor.getColumnIndexOrThrow("name")))
        }
        cursor.close()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sports)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}