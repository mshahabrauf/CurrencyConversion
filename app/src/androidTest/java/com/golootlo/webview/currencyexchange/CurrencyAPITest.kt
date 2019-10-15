package com.golootlo.webview.currencyexchange

import androidx.test.runner.AndroidJUnit4
import com.golootlo.webview.currencyexchange.network.RestClient
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Currency API test as it is working or access key has been expired
 */
@RunWith(AndroidJUnit4::class)
class CurrencyAPITest {

    @Test
    fun hitCurrencyApi(){
        val currency = RestClient.getRestAdapter()
            .getCurrencyRate(currencies = "USD,GBP", source = "USD").execute()
        Assert.assertEquals("API-Success-true", "API-Success-" + currency.body()?.success)
    }
}