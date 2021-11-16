package com.example.mymvvmsample.data.repositaries

import com.example.mymvvmsample.data.db.entites.AppDatabase
import com.example.mymvvmsample.data.db.entites.User
import com.example.mymvvmsample.data.network.MyApi
import com.example.mymvvmsample.data.network.SafeApiRequest
import com.example.mymvvmsample.data.network.responses.AuthResponse

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email: String, pass: String): AuthResponse {
        return apiRequest { api.useLogin(email, pass) }
    }

    suspend fun userSignUp(name: String, email: String, pass: String): AuthResponse {
        return apiRequest { api.userSignup(name, email, pass) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()

}