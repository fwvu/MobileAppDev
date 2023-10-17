package com.example.mobileappdev.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdev.R
import com.example.mobileappdev.adapters.CourseAdapter
import com.example.mobileappdev.models.CourseList

class CoursesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CourseAdapter
    private lateinit var courseArrayList: ArrayList<CourseList>

    lateinit var courseTitle: Array<String>
    lateinit var courseDesc: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitializer()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.courseRV)
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