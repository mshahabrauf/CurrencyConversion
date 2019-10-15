package com.golootlo.webview.currencyexchange.main

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.golootlo.webview.currencyexchange.R
import com.golootlo.webview.currencyexchange.callbacks.EditableTextWatcher
import com.golootlo.webview.currencyexchange.callbacks.ItemSelectedListener
import com.golootlo.webview.currencyexchange.callbacks.ResponseCallback
import com.golootlo.webview.currencyexchange.extensions.makeTimeString
import com.golootlo.webview.currencyexchange.extensions.showMessage
import com.golootlo.webview.currencyexchange.extensions.toCommaString
import com.golootlo.webview.currencyexchange.extras.ProgressLoader
import com.golootlo.webview.currencyexchange.models.Currency
import com.golootlo.webview.currencyexchange.network.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response




class MainActivity : AppCompatActivity() {


    private var et2Focus: Boolean = false
    private var et1Focus: Boolean = false
    private var forward: Boolean = true
    private lateinit var currencyResponse: Currency
    private var currentExchangeRate: Double = 1.0
    private var TAG = "MainActivity"
    private var CURRENCIES = arrayOf("USD", "GBP", "AUD", "CAD", "PLN", "MXN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
    }

    private fun fetchData(source: String) {
        ProgressLoader.progressLoader?.show(supportFragmentManager, "ProgressLoader")

        RestClient.getRestAdapter()
            .getCurrencyRate(currencies = CURRENCIES.toCommaString(), source = source)
            .enqueue(object : ResponseCallback<Currency>() {
                override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                    ProgressLoader.hideProgress()
                    response.body()?.apply {
                        if (response.body()?.success == true) {
                            currencyResponse = this
                            setDate(currencyResponse.timestamp)
                            makeCurrencyExchangeRate()
                        } else {
                            spinner.setSelection(0)
                            showMessage(response.body()?.error?.info.toString())
                        }

                    } ?: kotlin.run {
                        showMessage(getString(R.string.some_went_wrong))
                    }
                }
            })
    }

    private fun setDate(timestamp: Long?) {
        date.setText("Exchange rates were collected at " + timestamp?.makeTimeString())
    }

    private fun initializeViews() {
        currencyResponse = Currency()
        initSpinner(spinner)
        initSpinner(spinner2)
        setListeners()
    }

    private fun setListeners() {
        currency.addTextChangedListener(object : EditableTextWatcher() {
            override fun afterTextChange(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    if (et1Focus) {
                        val amount = s.toString().toDouble()

                        currency2.setText(String.format("%.3f", amount / currentExchangeRate))

                        currency2.setSelection(currency2.text.length)
                    }
                } else currency2.setText("")
            }
        })

        currency2.addTextChangedListener(object : EditableTextWatcher() {
            override fun afterTextChange(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    if (et2Focus) {
                        val amount = s.toString().toDouble()

                        currency.setText(String.format("%.3f", amount * currentExchangeRate))

                        currency.setSelection(currency.text.length)
                    }
                } else currency.setText("")
            }
        })

        spinner.onItemSelectedListener = object : ItemSelectedListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fetchData(p0?.selectedItem.toString())
            }
        }

        spinner2.onItemSelectedListener = object : ItemSelectedListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                makeCurrencyExchangeRate()
                currency2.setText(currency2.text.toString())
            }
        }

        currency.setOnFocusChangeListener({ view, b -> et1Focus = b })
        currency2.setOnFocusChangeListener({ view, b -> et2Focus = b })
    }

    private fun makeCurrencyExchangeRate() {
        currentExchangeRate =
            currencyResponse.quotes?.get(spinner.selectedItem.toString() + spinner2.selectedItem.toString())
                ?: kotlin.run { 1.0 }
    }

    private fun initSpinner(spinner1: Spinner) {
        val adapter = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item, CURRENCIES
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
    }
}
