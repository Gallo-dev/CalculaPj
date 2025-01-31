package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.calculapj.R

class ActivitySplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        //implementacao da tela Splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ActivitySobre::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}