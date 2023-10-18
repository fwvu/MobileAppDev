package com.example.mobileappdev.course_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mobileappdev.R
import com.example.mobileappdev.models.CourseList

class CourseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val getData = intent.getParcelableExtra<CourseList>("android")
        if (getData != null){
            val courseTitle: TextView = findViewById(R.id.courseTitleText)
            val courseCode: TextView = findViewById(R.id.courseCodeText)
            val courseInstructor: TextView = findViewById(R.id.courseInstructorText)
            val courseDescription: TextView = findViewById(R.id.courseDescriptionText)
            val coursePrerequisites: TextView = findViewById(R.id.coursePrerequisitesText)

            courseTitle.text = getData.dataCourseTitle
            courseCode.text = getData.dataCourseCode
            courseInstructor.text = getData.dataCourseInstructor
            courseDescription.text = getData.dataCourseDescription
            coursePrerequisites.text = getData.dataCoursePrerequisites

        }
    }
}