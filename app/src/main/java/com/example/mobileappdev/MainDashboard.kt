package com.example.mobileappdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobileappdev.fragments.CalenderFragment
import com.example.mobileappdev.fragments.HomeFragment
import com.example.mobileappdev.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainDashboard : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        bottomNavigationView = findViewById(R.id.bottom_nav)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_current -> {
                    //replaceFragment(CurrentFragment())
                    Toast.makeText(this,"Not yet implemented", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_calender -> {
                    replaceFragment(CalenderFragment())
                    true
                }
                R.id.bottom_profile -> {
                    replaceFragment(ProfileFragment())
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
}