package com.example.mymvvmsample.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmsample.R
import com.example.mymvvmsample.databinding.ActivityLoginBinding
import com.example.mymvvmsample.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val authViewModel: AuthViewModel = ViewModelProvider(this).get(
            AuthViewModel::class.java
        )
        binding.viewModel = authViewModel;
        authViewModel.authListener = this;
        binding.buttonSignIn.setOnClickListener { authViewModel.onLoginButtonClick(it) }
    }

    override fun onStarted() {
        toast("Login Started"); }

    override fun onSuccess() {
        toast("Login Success"); }


    override fun onFailure(message: String) {
        toast(message); }

}