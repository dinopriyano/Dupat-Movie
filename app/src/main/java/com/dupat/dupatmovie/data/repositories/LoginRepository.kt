package com.dupat.dupatmovie.data.repositories

import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.response.LoginResponse
import com.dupat.dupatmovie.data.network.SafeAPIRequest
import com.dupat.dupatmovie.ui.utils.Constant.Companion.baseAuth

class LoginRepository : SafeAPIRequest(){
    suspend fun userLogin(email: String, password: String) : LoginResponse{
        return apiRequest { APIInterface(baseAuth).userLogin(email,password) }
    }
}