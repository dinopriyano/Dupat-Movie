package com.dupat.dupatmovie.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.response.LoginResponse
import com.dupat.dupatmovie.data.session.SharedPrefManager
import com.dupat.dupatmovie.databinding.ActivityLoginBinding
import com.dupat.dupatmovie.ui.home.HomeActivity
import com.dupat.dupatmovie.ui.utils.snackbar
import com.dupat.dupatmovie.ui.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        if(SharedPrefManager.getToken(this,"login") == "yes")
        {
            startActivity(Intent(this,HomeActivity::class.java))
        }

        toolBar?.title = ""
        setSupportActionBar(toolBar)

        btnRegister?.setOnClickListener(this);

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRegister -> {
                startActivity(Intent(this,RegisterActivity::class.java))
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay)
                etUsername.text?.clear()
                etPassword.text?.clear()
                etUsername.requestFocus()
            }
        }
    }

    override fun onProcess() {

    }

    override fun onSuccess(response: Any) {
        if(response is LoginResponse) {
            SharedPrefManager.setToken(this, "login", "yes")
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }

    override fun onFailure(msg: String) {
        containerView.snackbar(msg)
    }
}