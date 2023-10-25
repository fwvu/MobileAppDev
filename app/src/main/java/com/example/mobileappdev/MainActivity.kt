package com.example.mobileappdev

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mobileappdev.course_search.CourseSearchActivity
import com.example.mobileappdev.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private var currentUserName: String? = null

    private val toLogin by lazy {
        Intent(this, LoginActivity::class.java)
    }

    private val toDashboard by lazy {
        Intent(this, MainDashboardActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        installSplashScreen()

        setContentView(R.layout.activity_main)

        loadUserData()

        if (currentUserName != null){
            startActivity(toDashboard)
        }else{
            startActivity(toLogin)
        }

    }
    private fun loadUserData() {
        // retrieve data from shard preferences
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        currentUserName = sharedPreferences.getString("USERNAME_KEY", null)

    }
}