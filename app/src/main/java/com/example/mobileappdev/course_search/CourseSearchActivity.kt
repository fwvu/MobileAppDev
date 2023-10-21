package com.example.mobileappdev.course_search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappdev.R
import com.example.mobileappdev.adapters.CourseAdapter
import com.example.mobileappdev.api.CourseDetailApi

import com.example.mobileappdev.models.CourseList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Locale

class CourseSearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cAdapter: CourseAdapter
    private lateinit var courseArrayList: ArrayList<CourseList> // Full course list
    private lateinit var courseArrayTitlesList: ArrayList<String> // List of dataCourseTitle

    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<CourseList>

    // retrofit builder
    //private val apiUrl = Base_Url_Singleton.baseUrl
    private val retrofitObj by lazy {
        Retrofit.Builder()
            .baseUrl("https://be52-115-166-11-141.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)

        recyclerView = findViewById(R.id.courseRV2)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // have to initialize the array here for null safety
        courseArrayList = ArrayList()
        courseArrayTitlesList = ArrayList()
        searchList = ArrayList()

        // Initialize the adapter but don't set it to the RecyclerView yet
        cAdapter = CourseAdapter(searchList)

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            // setup conditions for searchView to test
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    courseArrayList.forEach {
                        if ((it.dataCourseTitle.lowercase(Locale.getDefault()).contains(searchText))
                            || (it.dataCourseInstructor.lowercase(Locale.getDefault()).contains(searchText))
                            || (it.dataCourseDescription.lowercase(Locale.getDefault()).contains(searchText))
                            ){
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(courseArrayList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        // retrieve the data from the api and add it too the courseArrayList
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // create object of retrofit
                val api = retrofitObj.create(CourseDetailApi::class.java)
                val courseDetails = api.getCourseDetails()
                // Log.d("MelbAPPDemo", "Response received: $courseDetails")

                // Populate the full course list
                courseArrayList.clear() // Clear existing data if needed
                courseArrayList.addAll(courseDetails)
                searchList.addAll(courseArrayList)

                // Populate the list of dataCourseTitle
                courseArrayTitlesList.clear() // Clear existing data if needed
                val courseTitles = courseDetails.map { it.dataCourseTitle }
                courseArrayTitlesList.addAll(courseTitles)
                // Set the adapter and notify it to refresh the RecyclerView
                recyclerView.adapter = cAdapter
                // Notify the adapter to update the RecyclerView with courseTitles
                cAdapter.notifyDataSetChanged()

            } catch (e: Exception) {
                Log.e("MelbAPPDemo", "Error: ${e.message}")
            }
        }

        cAdapter.onItemClick ={
            val intent = Intent(this, CourseDetailsActivity::class.java)
            intent.putExtra("courseInfoLarge", it)
            startActivity(intent)
        }
    }
}