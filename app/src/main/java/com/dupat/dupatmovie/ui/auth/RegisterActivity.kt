package com.dupat.dupatmovie.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.data.network.response.RegisterResponse
import com.dupat.dupatmovie.databinding.ActivityLoginBinding
import com.dupat.dupatmovie.databinding.ActivityRegisterBinding
import com.dupat.dupatmovie.ui.utils.snackbar
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity(), View.OnClickListener,AuthListener {

    var toolbar:Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        toolbar = findViewById(R.id.toolBar)
        toolbar?.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnLogin?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin ->{
                onBackPressed()
            }
        }
    }

    override fun onProcess() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: Any) {
        when(response){
            is RegisterResponse ->{
                containerView.snackbar(response.message!!)
            }
        }
    }

    override fun onFailure(msg: String) {
        containerView.snackbar(msg)
    }
}