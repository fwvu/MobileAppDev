package com.example.mobileappdev.login


data class LoginResponse(
    val message: String,
    val username: String? = null,
    val studentid: String? = null,
    val email: String? = null,
    val phonenumber: String? = null
)


//data class LoginResponse(val message: String)
