package com.example.largeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TROUBLE WITH THIS

        /*textView6.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)

        }*/

    }

}
