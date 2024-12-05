package com.thaianramalho.trabalho3etapa

import android.content.ContentValues
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

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
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (!isValidDate(birthdate)) {
                    Toast.makeText(this, "Data de nascimento inválida", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

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
                    Toast.makeText(this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Falha ao registrar usuário", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date)
            true
        } catch (e: Exception) {
            false
        }
    }
}