package com.example.mobileappdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private var button: AppCompatButton? = null

    private val startApp by lazy {
        Intent(this, MainDashboard::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.startButton) as? AppCompatButton

        button?.setOnClickListener {
            startActivity(startApp)
        }

    }
}