package com.example.mymvvmsample.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("test")
    fun useLogin(@Field("email") email: String, @Field("password") pass: String): Call<ResponseBody>

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://purple.mocklab.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MyApi::class.java)
        }
    }
}
