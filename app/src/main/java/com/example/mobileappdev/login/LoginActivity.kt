package com.example.mobileappdev.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.mobileappdev.MainDashboardActivity
import com.example.mobileappdev.R

class LoginActivity : AppCompatActivity() {

    private lateinit var button: AppCompatButton
    private lateinit var studentId: AppCompatEditText
    private lateinit var password: AppCompatEditText

    private val toDashboard by lazy {
        Intent(this, MainDashboardActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button = findViewById(R.id.loginButton)
        studentId = findViewById(R.id.username)
        password = findViewById(R.id.password)

        button.setOnClickListener {
            val user = studentId.text.toString()
            val pass = password.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()){
                if (user == "a" && pass == "1") {
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(toDashboard)
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter Student Id and Password", Toast.LENGTH_SHORT).show()
            }

            // Syntax for adding a dataClass into Intent
            //toDashboard.putExtra("userInfoKey", UserData(studentName = "Bryan France", studentID = "s3892805"))

            //startActivity(toDashboard)
        }
    }
}