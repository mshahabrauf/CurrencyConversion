package com.golootlo.webview.currencyexchange.extensions

import android.text.TextUtils

fun Array<String>.toCommaString(): String{
    return TextUtils.join(",", this)
}