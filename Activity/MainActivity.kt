package com.example.foodbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodbuddy.API.ApiInterface
import com.example.foodbuddy.API.RetrofitInstance
import com.example.foodbuddy.API.SignInBody
import com.example.foodbuddy.PasswordUtils.salt
import com.example.foodbuddy.PasswordUtils.sha256
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moveREG = findViewById<TextView>(R.id.moveREG)
        // set on-click listener
        moveREG.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }

        val login = findViewById<Button>(R.id.login)
        // set on-click listener
        login.setOnClickListener{

            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = salt(findViewById<EditText>(R.id.password).text.toString()).sha256()

            signin(username,password)
        }
    }

    private fun signin(username: String, password: String)
    {
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        val signInInfo = SignInBody(username, password)
        val dash = Intent(this, NavbarActivity::class.java);

        retIn.signin(signInInfo).enqueue(object : Callback<ResponseBody>
        {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable)
            {
                Toast.makeText(
                        this@MainActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
            {
                if (response.code() == 200)
                {
                    val stringResponse = response.body()?.string()
                    Toast.makeText(this@MainActivity, "DEBUG", Toast.LENGTH_SHORT).show()
                    val json = JSONObject(stringResponse)

                    val fName = json.getString("fName")
                    val lName = json.getString("lName")
                    val email = json.getString("email")

                    Toast.makeText(this@MainActivity, fName, Toast.LENGTH_SHORT).show()

                    startActivity(dash)
                }
                else
                {
                    Toast.makeText(this@MainActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
