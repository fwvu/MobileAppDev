package com.example.mobileappdev.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdev.R
import com.example.mobileappdev.adapters.CourseAdapter
import com.example.mobileappdev.api.CourseDetailApi

import com.example.mobileappdev.course_search.CourseDetailsActivity
import com.example.mobileappdev.models.CourseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cAdapter: CourseAdapter
    private lateinit var courseArrayList: ArrayList<CourseList> // Full course list
    private lateinit var courseArrayTitlesList: ArrayList<String> // List of dataCourseTitle


    // retrofit builder
    //private val apiUrl = Base_Url_Singleton.baseUrl
    private val retrofitObj by lazy {
        Retrofit.Builder()
            .baseUrl("https://be52-115-166-11-141.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // create object of retrofit
        val api = retrofitObj.create(CourseDetailApi::class.java)
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.courseRVHome)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        // have to initialize the array here for null safety
        courseArrayList = ArrayList()
        courseArrayTitlesList = ArrayList()

        // call fetchFilteredCourses() to load filtered courses
        fetchFilteredCourses()

        cAdapter = CourseAdapter(courseArrayList)
        recyclerView.adapter = cAdapter


        cAdapter.onItemClick = { courseList ->
            val intent = Intent(requireContext(), CourseDetailsActivity::class.java) // requireContext used to get proper context
            intent.putExtra("courseInfoLarge", courseList)
            requireActivity().startActivity(intent)
        }
    }

    private fun fetchFilteredCourses() {
        val api = retrofitObj.create(CourseDetailApi::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Make an API call to fetch all course details
                val allCourseDetails = api.getCourseDetails()

                // Filter courses that contain "0" in any of the string fields
                val filteredCourses = allCourseDetails.filter { course ->
                    course.dataCourseTitle.contains("0") ||
                            course.dataCourseCode.contains("0") ||
                            course.dataCourseDescription.contains("0")
                }

                // Populate the full course list with filtered data
                courseArrayList.clear()
                courseArrayList.addAll(filteredCourses)

                // Populate the list of dataCourseTitle
                courseArrayTitlesList.clear()
                val courseTitles = filteredCourses.map { it.dataCourseTitle }
                courseArrayTitlesList.addAll(courseTitles)

                // Notify the adapter to update the RecyclerView with courseTitles
                cAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Log.e("MelbAPPDemo", "Error fetching filtered courses: ${e.message}")
            }
        }
    }

}