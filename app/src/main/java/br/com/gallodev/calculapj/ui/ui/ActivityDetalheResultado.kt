package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.calculapj.R
import br.com.gallodev.calculapj.databinding.ActivityDetalheResultadoBinding

class ActivityDetalheResultado : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        supportActionBar?.hide()
        title = "Detalhes"
        binding = ActivityDetalheResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buscaDetalhes()
    }

    override fun onResume() {
        super.onResume()
        recalcular()
    }

    private fun buscaDetalhes() {
        val mensagemValor = intent.getStringExtra("mensagemValor")
        val resultado = findViewById<TextView>(R.id.detalhe_valor_calculado)
        resultado.text = mensagemValor
        Log.d("ActivityDetalheResultado", "Resultado: $mensagemValor")
    }

    private fun recalcular(){
        binding.btCalcularNovamente.setOnClickListener {
            val calcularNovamente = Intent(this, ActivityInformacoes::class.java)
            startActivity(calcularNovamente)
            finish()
        }
    }

}