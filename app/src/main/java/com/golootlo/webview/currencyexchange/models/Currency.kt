package com.golootlo.webview.currencyexchange.models


class Currency : Meta() {
    var source: String? = null
    var quotes: Map<String, Double>? = null
}