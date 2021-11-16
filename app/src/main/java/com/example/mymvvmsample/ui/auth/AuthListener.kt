package com.example.mymvvmsample.ui.auth

import com.example.mymvvmsample.data.db.entites.User


interface AuthListener {
fun onStarted()
fun onSuccess(user: User)
fun onFailure(message: String)
}