package com.golootlo.webview.currencyexchange.extensions

import android.app.Activity
import android.widget.Toast

/**
 * @usage It use to show any message provided by the caller
 * @param view
 * @param message
 */
fun Activity.showMessage(message: String) {
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
}