package com.example.mymvvmsample.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mymvvmsample.data.repositaries.UserRepositaries

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    fun onLoginButtonClick() {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid User name or password")
            return

        } else {
            val loginResponse = UserRepositaries().userLogin(email!!, password!!)
            authListener?.onSuccess(loginResponse)
        }

    }
}