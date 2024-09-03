package com.example.rubtsova_diana_pr31_var4

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Bank : AppCompatActivity() {
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)

        loginField = findViewById(R.id.login)
        passwordField = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)

        loginButton.setOnClickListener {
            onLoginClicked()
        }
    }

    private fun onLoginClicked() {
        val login = loginField.text.toString()
        val password = passwordField.text.toString()

        if (login.isNotEmpty() && password.isNotEmpty()) {
            saveCredentials(login, password)
            showToast("Успешная регистрация!")
        } else {
            showToast("Заполните все поля!")
        }
    }

    private fun saveCredentials(login: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("login", login)
        editor.putString("password", password)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}