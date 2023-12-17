package com.example.foodapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.foodapp.models.User

class LoginActivity : AppCompatActivity() {
        private val usersList = ArrayList<User>()
        private lateinit var sharedPref: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            usersList.add(User("dereje", "dereje@gmail.com", "12345", ))

            val usernameEditText = findViewById<EditText>(R.id.textUsername)
            val passwordEditText = findViewById<EditText>(R.id.textPassword)
            val loginButton = findViewById<Button>(R.id.btnLogin)

            sharedPref = getSharedPreferences("Auth", Context.MODE_PRIVATE)

            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
            if (isLoggedIn) {
                navigateToHome()
            }

            loginButton.setOnClickListener {
                val enteredUsername = usernameEditText.text.toString().trim()
                val enteredPassword = passwordEditText.text.toString().trim()

                val userFound = usersList.find { it.username == enteredUsername && it.password == enteredPassword }

                if (userFound != null) {
                    saveLoginStatus(true, enteredUsername)
                    navigateToHome()
                } else {
                    val message = "Login failed. Invalid username or password."
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }


        private fun navigateToHome() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        private fun saveLoginStatus(isLoggedIn: Boolean, username: String) {
            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", isLoggedIn)
            editor.putString("username", username)
            editor.apply()
        }
    }
