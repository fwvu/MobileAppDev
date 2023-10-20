package com.example.mobileappdev.api

import com.example.mobileappdev.models.CourseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseDetailApi {

    //@GET("/courses/details")
    //suspend fun getCourseDetails(): CourseList
    @GET("/courses/details")
    suspend fun getCourseDetails(): List<CourseList>


    @GET("courses/filter")
    fun getFilteredCourses(@Query("filter") filter: String?): Call<List<CourseList>>
}