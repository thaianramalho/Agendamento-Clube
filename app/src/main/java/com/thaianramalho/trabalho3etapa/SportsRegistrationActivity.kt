package com.thaianramalho.trabalho3etapa

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SportsRegistrationActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_registration)

        dbHelper = DatabaseHelper(this)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()

            if (name.isNotEmpty()) {
                val db = dbHelper.readableDatabase
                val cursor = db.query(
                    "sports",
                    arrayOf("id"),
                    "name = ?",
                    arrayOf(name),
                    null,
                    null,
                    null
                )

                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Sport already exists", Toast.LENGTH_SHORT).show()
                } else {
                    cursor.close()
                    val values = ContentValues().apply {
                        put("name", name)
                    }
                    val newRowId = db.insert("sports", null, values)
                    if (newRowId != -1L) {
                        Toast.makeText(this, "Sport registered successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to register sport", Toast.LENGTH_SHORT).show()
                    }
                }
                cursor.close()
            } else {
                Toast.makeText(this, "Please fill the sport name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}