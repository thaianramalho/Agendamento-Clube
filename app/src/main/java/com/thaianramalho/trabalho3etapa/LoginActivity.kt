package com.thaianramalho.trabalho3etapa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val db = dbHelper.readableDatabase

                // Check if admin
                val adminCursor = db.query(
                    "admin",
                    arrayOf("id"),
                    "email = ? AND password = ?",
                    arrayOf(email, password),
                    null,
                    null,
                    null
                )

                if (adminCursor.moveToFirst()) {
                    // Admin login successful
                    val intent = Intent(this, AdminPanelActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Admin login successful", Toast.LENGTH_SHORT).show()
                } else {
                    // Check if user
                    val userCursor = db.query(
                        "user",
                        arrayOf("id"),
                        "email = ? AND password = ?",
                        arrayOf(email, password),
                        null,
                        null,
                        null
                    )

                    if (userCursor.moveToFirst()) {
                        // User login successful
                        val intent = Intent(this, UserPanelActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "User login successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                    userCursor.close()
                }
                adminCursor.close()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}