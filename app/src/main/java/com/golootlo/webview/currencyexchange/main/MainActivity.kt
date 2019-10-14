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
import com.golootlo.webview.currencyexchange.extensions.showMessage
import com.golootlo.webview.currencyexchange.extensions.toCommaString
import com.golootlo.webview.currencyexchange.models.Currency
import com.golootlo.webview.currencyexchange.network.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    private lateinit var currencyResponse: Currency
    private var currentExchangeRate = 1
    private var TAG = "MainActivity"
    private var CURRENCIES = arrayOf( "USD", "GDP", "AUD", "CAD", "PLN", "MXN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        fetchData()
    }

    private fun fetchData() {
        RestClient.getRestAdapter()
            .getCurrencyRate(currencies = CURRENCIES.toCommaString())
            .enqueue(object: ResponseCallback<Currency>(){
                override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                    response.body()?.apply {
                        currencyResponse = this
                    } ?: kotlin.run {
                        showMessage(getString(R.string.some_went_wrong))
                    }
                }
            })
    }

    private fun initializeViews() {
        initSpinner(spinner)
        initSpinner(spinner2)
        setListeners()
    }

    private fun setListeners() {
        currency.addTextChangedListener(object: EditableTextWatcher(){
            override fun afterTextChange(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    val amount = Integer.parseInt(s.toString())
                    currency2.setText("${amount * currentExchangeRate}")
                    currency2.setSelection(currency2.text.length)
                } else currency2.setText("")
            }
        })

        currency2.addTextChangedListener(object: EditableTextWatcher(){
            override fun afterTextChange(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    val amount = Integer.parseInt(s.toString())
                    currency.setText("${amount / currentExchangeRate}")
                    currency.setSelection(currency.text.length)
                } else currency.setText("")
            }
        })

        spinner.onItemSelectedListener = object : ItemSelectedListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }

        spinner2.onItemSelectedListener = object : ItemSelectedListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }
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
