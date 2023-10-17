package com.example.mobileappdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdev.adapters.CourseAdapter
import com.example.mobileappdev.models.CourseList

class CourseSearch : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter
    private lateinit var courseArrayList: ArrayList<CourseList>

    lateinit var courseTitle: Array<String>
    lateinit var courseDesc: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)


        dataInitializer()
        val layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.courseRV2)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CourseAdapter(courseArrayList)
        recyclerView.adapter = adapter
    }

    private fun dataInitializer(){
        courseArrayList = arrayListOf()

        courseTitle = arrayOf(
            "Course 1",
            "Course 2",
            "Course 3",
            "Course 4",
            "Course 5",
            "Course 6",
            "Course 7",
            "Course 8",
            "Course 9",
            "Course 10",
            "Course 11",
            "Course 12",
            "Course 13"
        )

        courseDesc = arrayOf(
            "course description 1",
            "course description 2",
            "course description 3",
            "course description 4",
            "course description 5",
            "course description 6",
            "course description 7",
            "course description 8",
            "course description 9",
            "course description 10",
            "course description 11",
            "course description 12",
            "course description 13"
        )

        for (i in courseTitle.indices){
            val course = CourseList(courseTitle[i])
            courseArrayList.add(course)
        }

    }
}