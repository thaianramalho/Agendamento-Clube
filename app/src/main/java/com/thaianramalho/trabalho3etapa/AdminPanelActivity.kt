package com.thaianramalho.trabalho3etapa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminPanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        val registerSportButton = findViewById<Button>(R.id.registerSportButton)
        val viewBookingsButton = findViewById<Button>(R.id.viewBookingsButton)

        registerSportButton.setOnClickListener {
            val intent = Intent(this, SportsRegistrationActivity::class.java)
            startActivity(intent)
        }

        viewBookingsButton.setOnClickListener {
            val intent = Intent(this, AdminViewBookingsActivity::class.java)
            startActivity(intent)
        }
    }
}