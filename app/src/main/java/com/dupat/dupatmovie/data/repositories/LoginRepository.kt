package com.dupat.dupatmovie.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.response.LoginResponse
import com.dupat.dupatmovie.data.network.response.SafeAPIRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository : SafeAPIRequest(){
    suspend fun userLogin(email: String, password: String) : LoginResponse{
        return apiRequest { APIInterface().userLogin(email,password) }
    }
}