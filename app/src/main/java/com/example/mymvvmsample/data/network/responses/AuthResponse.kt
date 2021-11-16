package com.example.mymvvmsample.data.network.responses

import com.example.mymvvmsample.data.db.entites.User

data class AuthResponse(
    val isSuccessful :Boolean?,
    val message: String?,
    val user:User?
)