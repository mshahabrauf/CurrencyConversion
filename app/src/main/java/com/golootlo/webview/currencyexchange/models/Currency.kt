package com.golootlo.webview.currencyexchange.models

import android.util.ArrayMap

class Currency : Meta() {
    var source: String? = null
    var qoutes: ArrayMap<String, Long>? = null
}