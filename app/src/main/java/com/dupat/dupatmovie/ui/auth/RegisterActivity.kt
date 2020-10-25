package com.dupat.dupatmovie.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.dupat.dupatmovie.R


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    var toolbar:Toolbar? = null
    var btnLogin: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        toolbar = findViewById(R.id.toolBar)
        toolbar?.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin ->{
                onBackPressed()
            }
        }
    }
}