package br.com.gallodev.calculapj.ui.extensions




private const val UM_DOZE_AVOS = 12.0
private const val FGTS_PERCENT = 0.08

fun calculaSalarioPjExtension(
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
    val contador = fgts - 250
    val das = contador - 70

    val valorCalculado = salarioClt + valeRefeicao + valeAlimentacao + valeTransporte + planoSaude + umDozeAvosDecimoTer + umDozeAvosFerias + umTercoFerias + das

    return valorCalculado
}



