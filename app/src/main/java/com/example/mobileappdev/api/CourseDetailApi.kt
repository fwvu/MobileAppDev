package com.example.mobileappdev.api

import com.example.mobileappdev.models.CourseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseDetailApi {

    @GET("/courses/details")
    suspend fun getCourseDetails(): List<CourseList>

}