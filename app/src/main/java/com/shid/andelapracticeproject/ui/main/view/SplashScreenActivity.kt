package com.shid.andelapracticeproject.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shid.andelapracticeproject.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            goToMainActivity()
        },3000)
    }

    private fun goToMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}