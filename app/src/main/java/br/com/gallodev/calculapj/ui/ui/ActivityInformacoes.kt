package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gallodev.calculapj.databinding.ActivityInformacoesBinding
import br.com.gallodev.calculapj.ui.extensions.calculaSalarioPjExtension
import br.com.gallodev.calculapj.ui.extensions.formatCurrency
import br.com.gallodev.calculapj.ui.extensions.formatoMoedaBrasileiraExtension
import java.math.BigDecimal


class ActivityInformacoes : AppCompatActivity() {

    private lateinit var binding: ActivityInformacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityInformacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Aplicando a formatação de moeda aos EditTexts
        binding.activityInformaDigitaValorClt.formatCurrency()
        binding.activityInformaDigitaVr.formatCurrency()
        binding.activityInformaDigitaVa.formatCurrency()
        binding.activityInformaDigitaVt.formatCurrency()
        binding.activityInformaDigitaPlanoSaude.formatCurrency()

    }

    override fun onResume() {
        super.onResume()
        calcular()
    }

    private fun obterValorDouble(editText: EditText): Double {
        val text = editText.text.toString()
        val apagaString = text.replace("R$", "").trim()

        // Remove todos os pontos (separadores de milhar)
        val removePonto = apagaString.replace(".", "")

        val preparaString = removePonto.replace(",", ".")

        return if (preparaString.isNotBlank()) {
            preparaString.toDouble()
        } else {
            0.0
        }
    }

    private fun calcularSalarioPj(): Double? {

        val salarioClt = obterValorDouble(binding.activityInformaDigitaValorClt)
        if ( binding.activityInformaDigitaValorClt.text.toString().isBlank()) {
            binding.activityInformaDigitaValorClt.error = "Campo Obrigatório"
            return null
        }

        val valeRefeicao = obterValorDouble(binding.activityInformaDigitaVr)
        val valeAlimentacao = obterValorDouble(binding.activityInformaDigitaVa)
        val valeTransporte = obterValorDouble(binding.activityInformaDigitaVt)
        val planoSaude = obterValorDouble(binding.activityInformaDigitaPlanoSaude)


        val resultado = calculaSalarioPjExtension(
            salarioClt,
            valeRefeicao,
            valeAlimentacao,
            valeTransporte,
            planoSaude
        )

        return resultado
    }


    private fun calcular() {
        //--------- Eventos de clique do botão calcular -----------
        binding.botaoCalcular.setOnClickListener {
            val valorCalculado = calcularSalarioPj()
            if (valorCalculado != null) {
                vaiParaDetalheResultado(valorCalculado)

            } else {
                Log.d(
                    "ActivityInformacoes",
                    "Valor calculado é nulo. Não vai para a tela de detalhe"
                )
                Toast.makeText(this, "Prencha o campo Obrigatório", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    private fun vaiParaDetalheResultado(valorCalculado: Double) {
        //---------- Função que busca e presenta o resultado formatado -----------
        val valorFormatado = formatoMoedaBrasileiraExtension(BigDecimal(valorCalculado))
        val mensagemValor = "\uD83D\uDCB0 $valorFormatado"

        val intent = Intent(this, ActivityDetalheResultado::class.java)
        intent.putExtra("mensagemValor", mensagemValor)
        startActivity(intent)

    }

}







