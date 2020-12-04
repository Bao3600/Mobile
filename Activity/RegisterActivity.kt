package com.example.foodbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import com.example.foodbuddy.API.ApiInterface
import com.example.foodbuddy.API.RetrofitInstance
import com.example.foodbuddy.API.UserBody
import com.example.foodbuddy.PasswordUtils.salt
import com.example.foodbuddy.PasswordUtils.sha256
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val fName = findViewById<EditText>(R.id.fName).text.toString()
            val lName = findViewById<EditText>(R.id.lName).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = salt(findViewById<EditText>(R.id.password).text.toString()).sha256()

            signup(username, fName, lName, email, password)

        }

    }
    private fun signup(username: String, fName: String,
                       lName: String, email: String,password: String){
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val registerInfo = UserBody(username,fName, lName, email, password)
        val reg = Intent(this, MainActivity::class.java)

        retIn.registerUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@RegisterActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {

                    val fName = findViewById<EditText>(R.id.fName).text.toString()
                    val lName = findViewById<EditText>(R.id.lName).text.toString()
                    val email = findViewById<EditText>(R.id.email).text.toString()
                    /****This is used to save the user information after successful login,
                     * which will be displayed on the My Account page***/
                    User.fName =fName
                    User.lName = lName
                    User.email = email

                    Toast.makeText(this@RegisterActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(reg)
                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}

