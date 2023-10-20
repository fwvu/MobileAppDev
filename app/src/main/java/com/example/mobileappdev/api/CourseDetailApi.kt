package com.example.mobileappdev.api

import com.example.mobileappdev.models.CourseList
import retrofit2.http.GET

interface CourseDetailApi {

    //@GET("/courses/details")
    //suspend fun getCourseDetails(): CourseList
    @GET("/courses/details")
    suspend fun getCourseDetails(): List<CourseList>
}