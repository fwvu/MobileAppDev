package com.example.mobileappdev.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.mobileappdev.BaseUrlSingleton
import com.example.mobileappdev.MainDashboardActivity
import com.example.mobileappdev.R
import com.example.mobileappdev.api.LoginApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginActivity : AppCompatActivity() {

    private var user = ""
    private var pass = ""

    // retrofit builder
    private val apiUrl = BaseUrlSingleton.baseUrl
    private val retrofitObj by lazy {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    private val loginApi: LoginApi by lazy {
        retrofitObj.create(LoginApi::class.java)
    }

    private val loginResponseLiveData = MutableLiveData<LoginResponse?>(null)

    private val toDashboard by lazy {
        Intent(this, MainDashboardActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        installSplashScreen()


        setContentView(R.layout.activity_login)

        loginResponseLiveData.observe(this) { response ->
            response?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                if (it.message == "Successful") {
                    startActivity(toDashboard)
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        findViewById<EditText>(R.id.username).addTextChangedListener {
            user = it.toString()
        }

        findViewById<EditText>(R.id.password).addTextChangedListener {
            pass = it.toString()
        }

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = loginApi.login(username = user, password = pass)
                    Log.d("MelbAPPDemo", response.toString()) // Log the response
                    getUserData = response
                    loginResponseLiveData.value = response
                    savedUserData()
                } catch (e: Exception) {
                    Log.e("MelbAPPDemo", "Network call failed $e")
                    loginResponseLiveData.value = LoginResponse(message = "Network call failed $e")
                }
            }
        }
    }
    private var getUserData: LoginResponse? = null

    private fun savedUserData() {
        // Access user info from getUserData
        val uName = getUserData?.username
        val uStudentid = getUserData?.studentid
        val uEmail = getUserData?.email
        val uPhone = getUserData?.phonenumber

        if (uName != null) {
            val sharedPreferences: SharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply {
                putString("USERNAME_KEY", uName)
                putString("USERSTUDENTID_KEY", uStudentid)
                putString("USEREMAIL_KEY", uEmail)
                putString("USERPHONE_KEY", uPhone)
            }.apply()
        }
        // Check uName after saving
        Log.d("MelbAPPDemo", "uName after saving: $uName")
    }
}