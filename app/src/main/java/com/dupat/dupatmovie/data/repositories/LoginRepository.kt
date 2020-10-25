package com.dupat.dupatmovie.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dupat.dupatmovie.data.network.APIInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    fun userLogin(email: String, password: String) : LiveData<String>{
        val loginResponse = MutableLiveData<String>()

        APIInterface().userLogin(email,password)
            .enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.code() == 200)
                    {
                        loginResponse.value = response.body()?.string()
                    }
                    else
                    {
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })

        return loginResponse
    }
}