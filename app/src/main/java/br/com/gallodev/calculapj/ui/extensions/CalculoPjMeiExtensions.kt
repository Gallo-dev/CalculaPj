package br.com.gallodev.calculapj.ui.extensions

import android.util.Log
import br.com.gallodev.calculapj.ui.ui.ActivityInformacoes

private const val UM_DOZE_AVOS = 12.0
private const val FGTS_PERCENT = 0.08

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
    val contador = fgts - 200
    val das = contador - 70

    Log.d("calculaSalarioPjExtension", "Salário CLT: $salarioClt")
    Log.d("calculaSalarioPjExtension", "Vale Refeição: $valeRefeicao")
    Log.d("calculaSalarioPjExtension", "Vale Alimentação: $valeAlimentacao")
    Log.d("calculaSalarioPjExtension", "Vale Transporte: $valeTransporte")
    Log.d("calculaSalarioPjExtension", "Plano de Saúde: $planoSaude")
    Log.d("calculaSalarioPjExtension", "1/12 Décimo Terceiro: $umDozeAvosDecimoTer")
    Log.d("calculaSalarioPjExtension", "1/12 Férias: $umDozeAvosFerias")
    Log.d("calculaSalarioPjExtension", "1/3 Férias: $umTercoFerias")
    Log.d("calculaSalarioPjExtension", "FGTS: $fgts")
    Log.d("calculaSalarioPjExtension", "Contador: $contador")
    Log.d("calculaSalarioPjExtension", "DAS: $das")

    val valorCalculado = salarioClt + valeRefeicao + valeAlimentacao + valeTransporte + planoSaude + umDozeAvosDecimoTer + umDozeAvosFerias + umTercoFerias + das
    Log.d("calculaSalarioPjExtension", "Resultado: $valorCalculado")

    return valorCalculado
}



