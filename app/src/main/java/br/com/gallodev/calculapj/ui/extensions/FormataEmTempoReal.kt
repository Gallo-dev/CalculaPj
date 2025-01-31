package br.com.gallodev.calculapj.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.Locale

fun EditText.formatCurrency() {
    val locale = Locale("pt", "BR") // Define o Locale para Real brasileiro
    val currencyFormat = NumberFormat.getCurrencyInstance(locale)

    this.addTextChangedListener(object : TextWatcher {
        private var current = ""
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            if (editable.toString() != current) {
                this@formatCurrency.removeTextChangedListener(this)

                val cleanString = editable.toString().replace(Regex("[^\\d]"), "") // Remove tudo que não for dígito
                if (cleanString.isEmpty()) {
                    this@formatCurrency.setText("")
                    this@formatCurrency.addTextChangedListener(this)
                    return
                }

                val parsed = cleanString.toDouble() / 100 // Divide por 100 para converter centavos
                val formattedCurrency = currencyFormat.format(parsed)

                current = formattedCurrency
                this@formatCurrency.setText(formattedCurrency)
                this@formatCurrency.setSelection(formattedCurrency.length)
                this@formatCurrency.addTextChangedListener(this)
            }
        }
    })
}