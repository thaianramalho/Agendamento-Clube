package com.thaianramalho.trabalho3etapa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val adminRegistrationButton = findViewById<Button>(R.id.adminRegistrationButton)
        val userRegistrationButton = findViewById<Button>(R.id.userRegistrationButton)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        adminRegistrationButton.setOnClickListener {
            val intent = Intent(this, AdminRegistrationActivity::class.java)
            startActivity(intent)
        }

        userRegistrationButton.setOnClickListener {
            val intent = Intent(this, UserRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}