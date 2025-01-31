package br.com.gallodev.calculapj.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import br.com.gallodev.calculapj.databinding.ActivityInformacoesBinding
import br.com.gallodev.calculapj.ui.extensions.calculaSalarioPjExtension
import br.com.gallodev.calculapj.ui.extensions.formatCurrency
import br.com.gallodev.calculapj.ui.extensions.formatoMoedaBrasileiraExtension
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


class ActivityInformacoes : AppCompatActivity() {

    private lateinit var binding: ActivityInformacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityInformacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--------- Formatação de moeda em tempo real -----------
        val salarioClt: EditText = binding.activityInformaDigitaValorClt
        salarioClt.formatCurrency()
        val valeRefeicao: EditText = binding.activityInformaDigitaVr
        valeRefeicao.formatCurrency()
        val valeAlimentacao: EditText = binding.activityInformaDigitaVa
        valeAlimentacao.formatCurrency()
        val valeTransporte: EditText = binding.activityInformaDigitaVt
        valeTransporte.formatCurrency()
        val planoSaude: EditText = binding.activityInformaDigitaPlanoSaude
        planoSaude.formatCurrency()

    }


    override fun onResume() {
        super.onResume()
        Log.d("ActivityInformacoes", "onResume() chamado")
        calcular()
    }

    private fun calcularSalarioPj(): Double? {
        //--------- Formatação de moeda -----------
        val Locale = Locale("pt,", "BR")
        val numberFormat = NumberFormat.getCurrencyInstance(Locale)

        val salarioCltTexto =
            binding.activityInformaDigitaValorClt.text.toString()
        //--------- Verificação de campos obrigatórios -----------
        if (salarioCltTexto.isBlank()) {
            binding.activityInformaDigitaValorClt.error = "Campo obrigatório"
            return null
        }
        //--------- Formatação de moeda -----------
        val salarioCltTextoConvertido = salarioCltTexto.replace(".", "").replace(",", ".")
        val salarioClt =
            if (salarioCltTextoConvertido.isNotBlank()) numberFormat.parse(salarioCltTextoConvertido)
                ?.toDouble() ?: 0.0 else 0.0
        val valeRefeicaoTexto = binding.activityInformaDigitaVr.text.toString()
        val valeRefeicaoTextoConvertido = valeRefeicaoTexto.replace(".", "").replace(",", ".")
        val valeRefeicao = if (valeRefeicaoTextoConvertido.isNotBlank()) numberFormat.parse(
            valeRefeicaoTextoConvertido
        )?.toDouble() ?: 0.0 else 0.0

        val valeAlimentacaoTexto = binding.activityInformaDigitaVa.text.toString()
        val valeAlimentacaoTextoConvertido = valeAlimentacaoTexto.replace(".", "").replace(",", ".")
        val valeAlimentacao = if (valeAlimentacaoTextoConvertido.isNotBlank()) numberFormat.parse(
            valeAlimentacaoTextoConvertido
        )?.toDouble() ?: 0.0 else 0.0

        val valeTransporteTexto = binding.activityInformaDigitaVt.text.toString()
        val valeTransporteTextoConvertido = valeTransporteTexto.replace(".", "").replace(",", ".")
        val valeTransporte = if (valeTransporteTextoConvertido.isNotBlank()) numberFormat.parse(
            valeTransporteTextoConvertido
        )?.toDouble() ?: 0.0 else 0.0

        val planoSaudeTexto = binding.activityInformaDigitaPlanoSaude.text.toString()
        val planoSaudeTextoConvertido = planoSaudeTexto.replace(".", "").replace(",", ".")
        val planoSaude =
            if (planoSaudeTextoConvertido.isNotBlank()) numberFormat.parse(planoSaudeTextoConvertido)
                ?.toDouble() ?: 0.0 else 0.0


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
            Log.d("ActivityInformacoes", "Botão Calcular clicado")
            val valorCalculado = calcularSalarioPj()
            if (valorCalculado != null) {
                Log.d(
                    "ActivityInformacoes",
                    "Valor calculado: $valorCalculado. Vai para a tela de detalhe"
                )
                vaiParaDetalheResultado(valorCalculado)
            } else {
                Log.d(
                    "ActivityInformacoes",
                    "Valor calculado é nulo. Não vai para a tela de detalhe"
                )
                Toast.makeText(this, "Prencha o campo Obrigatório", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun vaiParaDetalheResultado(valorCalculado: Double) {
        //---------- Função que busca e presenta o resultado formatado -----------
        val valorFormatado = formatoMoedaBrasileiraExtension(BigDecimal(valorCalculado))

        val mensagemValor = "\uD83D\uDCB0 ${valorFormatado}"

        val intent = Intent(this, ActivityDetalheResultado::class.java)
        intent.putExtra("mensagemValor", mensagemValor)
        startActivity(intent)

    }

}






