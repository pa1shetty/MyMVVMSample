package com.example.mymvvmsample.data.network

import com.example.mymvvmsample.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field(nameLbl) name: String,
        @Field(emailLbl) email: String,
        @Field(passwordLbl) pass: String
    ): Response<AuthResponse>


    @FormUrlEncoded
    @POST("login")
    suspend fun useLogin(
        @Field(emailLbl) email: String,
        @Field(passwordLbl) pass: String
    ): Response<AuthResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrlLbl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MyApi::class.java)
        }
    }
}
