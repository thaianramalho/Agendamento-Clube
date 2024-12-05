package com.thaianramalho.trabalho3etapa

import android.content.ContentValues
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminRegistrationActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_registration)

        dbHelper = DatabaseHelper(this)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put("name", name)
                    put("email", email)
                    put("password", password) // Use a secure method to store passwords
                }
                val newRowId = db.insert("admin", null, values)
                if (newRowId != -1L) {
                    Toast.makeText(this, "Administrador registrado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Falha ao registrar administrador", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}