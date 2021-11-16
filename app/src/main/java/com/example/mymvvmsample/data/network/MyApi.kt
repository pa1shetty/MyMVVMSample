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
    @POST("test")
    suspend fun useLogin(
        @Field("email") email: String, @Field("password") pass: String
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
                .baseUrl("https://purple.mocklab.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MyApi::class.java)
        }
    }
}
