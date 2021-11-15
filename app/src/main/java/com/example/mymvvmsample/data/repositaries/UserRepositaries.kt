package com.example.mymvvmsample.data.repositaries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymvvmsample.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositaries {
    fun userLogin(email: String, pass: String): LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        MyApi().useLogin(email, pass).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body()?.string()
                } else {
                    loginResponse.value = response.errorBody()?.string()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.message
            }

        }
        )
        return loginResponse
    }
}