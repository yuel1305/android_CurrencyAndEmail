package com.example.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var editTextFromAmount: EditText
    private lateinit var editTextToAmount: EditText
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner

    private val exchangeRates = mapOf(
        "USD" to 1.0,
        "VND" to 24000.0,
        "EUR" to 0.85,
        "JPY" to 110.0,
        "GBP" to 0.75
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFromAmount = findViewById(R.id.editTextFromAmount)
        editTextToAmount = findViewById(R.id.editTextToAmount)
        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)

        val currencies = arrayOf("USD", "VND", "EUR", "JPY", "GBP")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        spinnerFromCurrency.setSelection(0)
        spinnerToCurrency.setSelection(1)

        fun convertCurrency(amount: Double, from: String, to: String): Double {
            val fromRate = exchangeRates[from] ?: 1.0
            val toRate = exchangeRates[to] ?: 1.0
            return (amount / fromRate) * toRate
        }

        editTextFromAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editTextFromAmount.hasFocus()) {
                    val fromCurrency = spinnerFromCurrency.selectedItem.toString()
                    val toCurrency = spinnerToCurrency.selectedItem.toString()
                    val fromAmount = s.toString().toDoubleOrNull()

                    if (fromAmount != null) {
                        val convertedAmount = convertCurrency(fromAmount, fromCurrency, toCurrency)
                        editTextToAmount.setText(String.format("%.2f", convertedAmount))
                    } else {
                        editTextToAmount.text.clear()
                    }
                }
            }
        })

        editTextToAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editTextToAmount.hasFocus()) {
                    val fromCurrency = spinnerToCurrency.selectedItem.toString()
                    val toCurrency = spinnerFromCurrency.selectedItem.toString()
                    val toAmount = s.toString().toDoubleOrNull()

                    if (toAmount != null) {
                        val convertedAmount = convertCurrency(toAmount, fromCurrency, toCurrency)
                        editTextFromAmount.setText(String.format("%.2f", convertedAmount))
                    } else {
                        editTextFromAmount.text.clear()
                    }
                }
            }
        })
    }
}
