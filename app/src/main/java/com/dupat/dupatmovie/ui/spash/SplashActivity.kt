package com.dupat.dupatmovie.ui.spash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dupat.dupatmovie.R
import com.dupat.dupatmovie.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right,R.anim.stay)
            finish()
        },2000)
    }
}