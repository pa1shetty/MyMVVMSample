package com.example.mymvvmsample.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmsample.R
import com.example.mymvvmsample.data.db.entites.User
import com.example.mymvvmsample.databinding.ActivitySignupBinding
import com.example.mymvvmsample.ui.home.HomeActivity
import com.example.mymvvmsample.utils.hide
import com.example.mymvvmsample.utils.show
import com.example.mymvvmsample.utils.snackBar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignupBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val authViewModel: AuthViewModel = ViewModelProvider(this, factory).get(
            AuthViewModel::class.java
        )
        binding.viewModel = authViewModel
        authViewModel.authListener = this
        binding.buttonSignUp.setOnClickListener { authViewModel.onSignUpButtonClick() }
        binding.textViewLogin.setOnClickListener { onBackPressed() }
        authViewModel.getLoggedInUser().observe(this, { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackBar("${user.email} has logged in")
    }


    override fun onFailure(message: String) {
        root_layout.snackBar(message)
        progress_bar.hide()
    }
}