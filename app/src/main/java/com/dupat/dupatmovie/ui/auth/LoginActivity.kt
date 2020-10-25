package com.dupat.dupatmovie.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.databinding.ActivityLoginBinding
import com.dupat.dupatmovie.ui.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        toolBar?.title = ""
        setSupportActionBar(toolBar)

        btnRegister?.setOnClickListener(this);

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRegister -> {
                startActivity(Intent(this,RegisterActivity::class.java))
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay)
            }
        }


    }

    override fun onProcess() {
        toast("Login Clicked!")

    }

    override fun onSuccess(response: LiveData<String>) {
        response.observe(this, Observer {
            toast(it)
        })
    }

    override fun onFailure(msg: String) {
        toast(msg)
    }
}