package com.dupat.dupatmovie.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onProcess()
    fun onSuccess(response: LiveData<String>)
    fun onFailure(msg: String)
}