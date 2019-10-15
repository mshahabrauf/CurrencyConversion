package com.golootlo.webview.currencyexchange.models

open class Meta {
    var success: Boolean = false
    var error: Error? = null
    var terms: String? = null
    var privacy: String? = null
    var timestamp: Long? = null

    inner class Error {
        var info: String? = null
    }
}