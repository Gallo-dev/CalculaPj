package br.com.gallodev.calculapj.ui.extensions

import android.util.Log
import br.com.gallodev.calculapj.ui.ui.ActivityInformacoes

private const val UM_DOZE_AVOS = 12.0
private const val FGTS_PERCENT = 0.08
private const val VALOR_FIXO_CONTADOR = 200.0;
private const val VALOR_FIXO_DAS = 70.0;

fun ActivityInformacoes.calculaSalarioPjExtension(
    salarioClt: Double,
    valeRefeicao: Double,
    valeAlimentacao: Double,
    valeTransporte: Double,
    planoSaude: Double
): Double {
    val umDozeAvosDecimoTer = salarioClt / UM_DOZE_AVOS
    val umDozeAvosFerias = salarioClt / UM_DOZE_AVOS
    val umTercoFerias = salarioClt / 3
    val fgts = FGTS_PERCENT * salarioClt
    val contador = fgts - VALOR_FIXO_CONTADOR
    val das = contador - VALOR_FIXO_DAS


    val valorCalculado = salarioClt + valeRefeicao + valeAlimentacao + valeTransporte + planoSaude + umDozeAvosDecimoTer + umDozeAvosFerias + umTercoFerias + das
    Log.d("calculaSalarioPjExtension", "Resultado: $valorCalculado")

    return valorCalculado
}



