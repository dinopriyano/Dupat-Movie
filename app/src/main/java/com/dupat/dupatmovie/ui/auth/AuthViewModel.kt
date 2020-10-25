package com.dupat.dupatmovie.ui.auth

import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import com.dupat.dupatmovie.data.repositories.LoginRepository

class AuthViewModel: ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener:AuthListener? = null

    fun onLoginButtonClick(v: View)
    {
        if(email.isNullOrEmpty())
        {
            authListener?.onFailure("Username not valid!")
        }
        else if(password.isNullOrEmpty() || password?.length!! < 6)
        {
            authListener?.onFailure("Password length must more than 5!")
        }
        else
        {
            val response = LoginRepository().userLogin(email!!,password!!)
            authListener?.onSuccess(response)
        }
    }

}