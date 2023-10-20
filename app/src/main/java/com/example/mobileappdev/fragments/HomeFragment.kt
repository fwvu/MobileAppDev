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
    private val retrofitObj by lazy {
        Retrofit.Builder()
            .baseUrl("https://320e-115-166-11-141.ngrok-free.app")
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

        cAdapter = CourseAdapter(courseArrayList)
        recyclerView.adapter = cAdapter

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val filter = "0"
                val response = api.getFilteredCourses(filter).execute()
                if (response.isSuccessful) {
                    val courseDetails = response.body()
                    if (courseDetails != null) {
                        // Log the data to ensure you received it
                        Log.d("MelbAPPDemo", "Received ${courseDetails.size} course details")
                        courseArrayList.clear()
                        courseArrayList.addAll(courseDetails)
                        cAdapter.notifyDataSetChanged()
                    } else {
                        Log.e("MelbAPPDemo", "Course details are null")
                    }
                } else {
                    Log.e("MelbAPPDemo", "API call not successful: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MelbAPPDemo", "Error: ${e.message}")
            }
        }

        cAdapter.onItemClick = { courseList ->
            val intent = Intent(requireContext(), CourseDetailsActivity::class.java) // requireContext used to get proper context
            intent.putExtra("courseInfoLarge", courseList)
            requireActivity().startActivity(intent)
        }
    }
}