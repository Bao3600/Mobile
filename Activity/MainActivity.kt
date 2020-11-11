package com.example.foodbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moveREG = findViewById<TextView>(R.id.moveREG)
// set on-click listener
        moveREG.setOnClickListener {
            // your code to run when the user clicks on the TextView
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }

    }
}
