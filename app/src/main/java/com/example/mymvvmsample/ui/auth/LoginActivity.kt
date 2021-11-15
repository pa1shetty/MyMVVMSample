package com.example.mymvvmsample.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmsample.R
import com.example.mymvvmsample.databinding.ActivityLoginBinding
import com.example.mymvvmsample.utils.hide
import com.example.mymvvmsample.utils.logOver
import com.example.mymvvmsample.utils.show
import com.example.mymvvmsample.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val authViewModel: AuthViewModel = ViewModelProvider(this).get(
            AuthViewModel::class.java
        )
        binding.viewModel = authViewModel
        authViewModel.authListener = this
        binding.buttonSignIn.setOnClickListener { authViewModel.onLoginButtonClick() }

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, {
            progress_bar.hide()
            logOver("log1",it)
            toast(it)

        })
    }


    override fun onFailure(message: String) {
        toast(message)
        progress_bar.hide()
    }


}