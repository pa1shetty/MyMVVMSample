package com.example.mymvvmsample.data.network

import com.example.mymvvmsample.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuffer()
            val error = response.errorBody()?.toString()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                    message.append("\n")
                } catch (e: JSONException) {
                }
            }
            message.append("Error code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}