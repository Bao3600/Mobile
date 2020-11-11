package com.example.foodbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val moveLOGIN = findViewById<TextView>(R.id.moveLOGIN)
// set on-click listener
        moveLOGIN.setOnClickListener {
            // your code to run when the user clicks on the TextView
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }


    }
}
