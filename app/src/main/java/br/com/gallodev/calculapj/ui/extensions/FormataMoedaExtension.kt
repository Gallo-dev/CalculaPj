package br.com.gallodev.calculapj.ui.extensions

import br.com.gallodev.calculapj.ui.ui.ActivityInformacoes
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun formatoMoedaBrasileiraExtension(valor: BigDecimal): String {
    val formato: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return formato.format(valor)
}