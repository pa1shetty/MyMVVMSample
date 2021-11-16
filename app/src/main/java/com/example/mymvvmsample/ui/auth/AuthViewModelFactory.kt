package com.example.mymvvmsample.ui.auth

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmsample.data.repositaries.UserRepository

class AuthViewModelFactory(
    private val application: Application,
 private val  repository: UserRepository
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(application,repository) as T
    }
}