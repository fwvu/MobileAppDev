package com.example.mobileappdev.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import com.example.mobileappdev.MainDashboardActivity
import com.example.mobileappdev.R
import com.example.mobileappdev.login.LoginActivity

class ProfileFragment : Fragment() {

    private var logoutButton: AppCompatButton? = null

    private val toLogin by lazy {
        Intent(requireContext(), LoginActivity::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentName = view.findViewById<TextView>(R.id.userTitleText)
        val fragmentEmail = view.findViewById<TextView>(R.id.userEmailText)
        val fragmentPhone = view.findViewById<TextView>(R.id.userPhoneText)

        val fragmentContext = requireContext()

        val sharedPreferences: SharedPreferences = fragmentContext.getSharedPreferences("userDetails", Context.MODE_PRIVATE)
        val profileName = sharedPreferences.getString("USERNAME_KEY", "User")
        val profileEmail = sharedPreferences.getString("USEREMAIL_KEY", "User")
        val profilePhone = sharedPreferences.getString("USERPHONE_KEY", "1800-Rylie-Bad")

        fragmentName.text = profileName
        fragmentEmail.text = profileEmail
        fragmentPhone.text = profilePhone

        logoutButton = view.findViewById(R.id.logoutButton) as? AppCompatButton

        logoutButton?.setOnClickListener {
            val sharedPreferencesEditor = sharedPreferences.edit()
            sharedPreferencesEditor.clear()
            sharedPreferencesEditor.apply()
            startActivity(toLogin)
        }
    }
}