package com.golootlo.webview.currencyexchange.callbacks

import android.util.Log
import retrofit2.Call
import retrofit2.Response

abstract class ResponseCallback<T> : retrofit2.Callback<T> {
    var TAG = "ResponseCallback"
    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, t.message ?: kotlin.run { "" })
    }

    abstract override fun onResponse(call: Call<T>, response: Response<T>)
}