package com.example.mobileappdev.login


data class LoginResponse(
    val message: String,
    val username: String? = null,
    val studentid: String? = null,
    val email: String? = null,
    val phone: String? = null
)


//data class LoginResponse(val message: String)
