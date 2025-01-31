package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.gallodev.calculapj.R
import br.com.gallodev.calculapj.databinding.ActivitySobreBinding

class ActivitySobre : AppCompatActivity() {

    private lateinit var binding: ActivitySobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vaiParaProximo()
        vaiParaPaginaInformacoes()
    }

    private fun vaiParaProximo() {
        binding.proximo.setOnClickListener {
            val intent = Intent(this, ActivitySobreDois::class.java)
            startActivity(intent)
        }
    }
    private fun vaiParaPaginaInformacoes() {
        binding.pular.setOnClickListener {
            val intent = Intent(this, ActivityInformacoes::class.java)
            startActivity(intent)
        }
    }
}