package com.dupat.dupatmovie.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.dupat.dupatmovie.data.repositories.LoginRepository
import com.dupat.dupatmovie.data.repositories.RegisterRepository
import com.dupat.dupatmovie.ui.utils.APIExceptions
import com.dupat.dupatmovie.ui.utils.Corountines

class AuthViewModel: ViewModel() {

    var email: String? = null
    var password: String? = null
    var name: String? = null

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
            Corountines.main {
                try {
                    val response = LoginRepository().userLogin(email!!,password!!)
                    response.let {
                        authListener?.onSuccess(it)
                        return@main
                    }

                    authListener?.onFailure(response.message!!)
                }
                catch (e: APIExceptions)
                {
                    authListener?.onFailure(e.message!!)
                }
            }
        }
    }

    fun onRegisterClick(v: View)
    {
        if(email.isNullOrEmpty())
        {
            authListener?.onFailure("Username not valid!")
        }
        else if(name.isNullOrEmpty())
        {
            authListener?.onFailure("Name must be filled!")
        }
        else if(password.isNullOrEmpty() || password?.length!! < 6)
        {
            authListener?.onFailure("Password length must more than 5!")
        }
        else
        {
            Corountines.main {
                try {
                    val response = RegisterRepository().userRegister(email!!,password!!,name!!)
                    response.let {
                        authListener?.onSuccess(it)
                        return@main
                    }

                    authListener?.onFailure(response.message!!)
                }
                catch (e: APIExceptions)
                {
                    authListener?.onFailure(e.message!!)
                }
            }
        }
    }

}