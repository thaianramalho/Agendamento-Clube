package com.thaianramalho.trabalho3etapa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        dbHelper = DatabaseHelper(this)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$year-${month + 1}-$dayOfMonth"
            // Check if the date is available
            if (isDateAvailable(selectedDate)) {
                // Navigate to the activity selection screen
                val intent = Intent(this, ActivitySelectionActivity::class.java)
                intent.putExtra("selectedDate", selectedDate)
                startActivity(intent)
            } else {
                Toast.makeText(this, "A data já está reservada", Toast.LENGTH_SHORT).show()
            }
        }

        // Mark occupied dates
        markOccupiedDates(calendarView)
    }

    private fun isDateAvailable(date: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "bookings",
            arrayOf("id"),
            "date = ?",
            arrayOf(date),
            null,
            null,
            null
        )
        val isAvailable = !cursor.moveToFirst()
        cursor.close()
        return isAvailable
    }


    private fun markOccupiedDates(calendarView: CalendarView) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "bookings",
            arrayOf("date"),
            null,
            null,
            null,
            null,
            null
        )
        val bookedDates = mutableListOf<String>()
        while (cursor.moveToNext()) {
            bookedDates.add(cursor.getString(cursor.getColumnIndexOrThrow("date")))
        }
        cursor.close()

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()

        bookedDates.forEach { date ->
            calendar.time = sdf.parse(date)!!
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
        }

        val listView = findViewById<ListView>(R.id.unavailableDatesListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookedDates)
        listView.adapter = adapter
    }
}