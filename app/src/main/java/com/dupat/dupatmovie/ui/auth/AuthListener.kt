package com.dupat.dupatmovie.ui.auth

import com.dupat.dupatmovie.data.network.response.LoginResponse

interface AuthListener {
    fun onProcess()
    fun onSuccess(response: Any)
    fun onFailure(msg: String)
}