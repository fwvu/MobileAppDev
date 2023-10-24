package com.example.mobileappdev

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobileappdev.course_search.CourseSearchActivity
import com.example.mobileappdev.fragments.CoursesFragment
import com.example.mobileappdev.fragments.ScheduleFragment
import com.example.mobileappdev.fragments.HomeFragment
import com.example.mobileappdev.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private val searchActivity by lazy {
        Intent(this, CourseSearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        loadUserData()

        bottomNavigationView = findViewById(R.id.bottom_nav)

        // bottom nav bar and the links
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_courses -> {
                    //replaceFragment(CoursesFragment())
                    startActivity(searchActivity)
                    true
                }
                R.id.bottom_schedule -> {
                    replaceFragment(ScheduleFragment())
                    Toast.makeText(this,"Not yet implemented", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_profile -> {
                    replaceFragment(ProfileFragment())
                    //replaceFragment(CoursesFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(HomeFragment())

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_wrapper, fragment).commit()
    }

    private fun loadUserData(){
        // retrieve data from shard preferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        val currentUserName:String? = sharedPreferences.getString("USERNAME_KEY", "User")
        val currentUserStudentid:String? = sharedPreferences.getString("USERSTUDENTID_KEY", "Student ID")

        // adds users name and student id to header
        val headerName = findViewById<TextView>(R.id.headerUsername)
        val headerStudentid = findViewById<TextView>(R.id.headerStudentID)

        headerName.text = currentUserName
        headerStudentid.text = currentUserStudentid
    }
}