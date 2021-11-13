package com.example.mymvvmsample.ui.auth

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var email: String? = null;
    var password: String? = null;
    var authListener: AuthListener? = null;
    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid User name or password")
            return

        } else {
            authListener?.onSuccess()
        }

    }
}