package com.dupat.dupatmovie.data.network

import com.dupat.dupatmovie.ui.utils.APIExceptions
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.lang.StringBuilder

abstract class SafeAPIRequest {
    suspend fun <T : Any> apiRequest(call : suspend() -> Response<T>) : T{

        val response = call.invoke()

        if(response.isSuccessful)
        {
            return response.body()!!
        }
        else
        {
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            error?.let {
                try
                {
                    message.append(JSONObject(it).getString("message"))
                }
                catch (e: Exception)
                {
                    message.append("Error code ${response.code()}")
                }
            }

            throw APIExceptions(message.toString())
        }

    }
}