package com.thaianramalho.trabalho3etapa

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserRegistrationActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        dbHelper = DatabaseHelper(this)

        val cpfEditText = findViewById<EditText>(R.id.cpfEditText)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val birthdateEditText = findViewById<EditText>(R.id.birthdateEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val cpf = cpfEditText.text.toString()
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()
            val birthdate = birthdateEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (cpf.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && birthdate.isNotEmpty() && password.isNotEmpty()) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put("cpf", cpf)
                    put("name", name)
                    put("phone", phone)
                    put("email", email)
                    put("birthdate", birthdate)
                    put("password", password) // Use a secure method to store passwords
                }
                val newRowId = db.insert("user", null, values)
                if (newRowId != -1L) {
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}