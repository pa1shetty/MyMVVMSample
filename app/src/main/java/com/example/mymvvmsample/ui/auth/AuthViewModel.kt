package com.example.mymvvmsample.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mymvvmsample.data.repositaries.UserRepository
import com.example.mymvvmsample.utils.ApiException
import com.example.mymvvmsample.utils.Coroutines
import com.example.mymvvmsample.utils.NoInternetException

class AuthViewModel(
    application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(application) {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null
    fun getLoggedInUser()=userRepository.getUser()

    fun onLoginButtonClick() {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid User name or password")
            return

        } else {
            Coroutines.main {
                try {
                    val authResponse = userRepository.userLogin(email!!, password!!)
                    authResponse.user?.let {
                        authListener?.onSuccess(it)
                        userRepository.saveUser(it)
                        return@main
                    }
                    authListener?.onFailure(authResponse.message!!)
                } catch (e: ApiException) {
                    authListener?.onFailure(e.message!!)
                }
                catch (e:NoInternetException){
                    authListener?.onFailure(e.message!!)
                }
            }
        }

    }
}