package com.thaianramalho.trabalho3etapa

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AdminViewBookingsActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view_bookings)

        dbHelper = DatabaseHelper(this)

        val bookingsListView = findViewById<ListView>(R.id.bookingsListView)
        populateBookingsListView(bookingsListView)
    }

    private fun populateBookingsListView(listView: ListView) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "bookings",
            arrayOf("date", "sport"),
            null,
            null,
            null,
            null,
            "date ASC"
        )
        val bookings = mutableListOf<String>()
        while (cursor.moveToNext()) {
            val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
            val sport = cursor.getString(cursor.getColumnIndexOrThrow("sport"))
            bookings.add("$date: $sport")
        }
        cursor.close()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookings)
        listView.adapter = adapter
    }
}