package com.golootlo.webview.currencyexchange

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.golootlo.webview.currencyexchange.callbacks.ResponseCallback
import com.golootlo.webview.currencyexchange.extensions.showMessage
import com.golootlo.webview.currencyexchange.extensions.toCommaString
import com.golootlo.webview.currencyexchange.extras.ProgressLoader
import com.golootlo.webview.currencyexchange.models.Currency
import com.golootlo.webview.currencyexchange.network.RestClient
import kotlinx.android.synthetic.main.activity_main.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.golootlo.webview.currencyexchange", appContext.packageName)
    }
}
