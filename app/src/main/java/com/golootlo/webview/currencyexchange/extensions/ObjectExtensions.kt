package com.golootlo.webview.currencyexchange.extensions

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

fun Array<String>.toCommaString(): String{
    return TextUtils.join(",", this)
}

fun Long.makeTimeString() : String {
    val date = Date(this * 1000)
    val sdf = SimpleDateFormat("d MMM yyyy h:mm a") // the format of your date
    return sdf.format(date)
}