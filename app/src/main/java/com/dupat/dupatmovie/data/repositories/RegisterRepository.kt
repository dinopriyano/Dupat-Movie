package com.dupat.dupatmovie.data.repositories

import com.dupat.dupatmovie.data.network.APIInterface
import com.dupat.dupatmovie.data.network.SafeAPIRequest
import com.dupat.dupatmovie.data.network.response.RegisterResponse
import com.dupat.dupatmovie.ui.utils.Constant.Companion.baseAuth

class RegisterRepository: SafeAPIRequest() {
    suspend fun userRegister(email: String, password: String, name: String) : RegisterResponse{
        return apiRequest{APIInterface(baseAuth).userRegister(email, password, name)}
    }
}