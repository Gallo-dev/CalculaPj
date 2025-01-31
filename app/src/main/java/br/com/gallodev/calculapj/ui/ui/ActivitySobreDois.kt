package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.calculapj.databinding.ActivitySobreDoisBinding

class ActivitySobreDois : AppCompatActivity() {
    private lateinit var binding: ActivitySobreDoisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        binding = ActivitySobreDoisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        vaiParaPaginaDois()
        voltar()
    }

    private fun vaiParaPaginaDois(){
        binding.proximo.setOnClickListener {
            val intent = Intent(this, ActivityInformacoes::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun voltar(){
        binding.voltar.setOnClickListener {
            val voltaPagina = Intent(this, ActivitySobre::class.java)
            startActivity(voltaPagina)
            finish()
        }
    }

}