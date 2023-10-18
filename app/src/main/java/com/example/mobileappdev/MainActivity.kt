package com.example.mobileappdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.mobileappdev.course_search.CourseSearchActivity
import com.example.mobileappdev.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private var button: AppCompatButton? = null
    private var button2: AppCompatButton? = null

    private val startApp by lazy {
        Intent(this, LoginActivity::class.java)
    }

    private val searchActivity by lazy {
        Intent(this, CourseSearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.startButton) as? AppCompatButton

        button?.setOnClickListener {
            startActivity(startApp)
        }

        button2 = findViewById(R.id.sTest) as? AppCompatButton

        button2?.setOnClickListener {
            startActivity(searchActivity)
        }

    }
}