package com.golootlo.webview.currencyexchange.network

import com.golootlo.webview.currencyexchange.models.Currency
import retrofit2.Call
import retrofit2.http.*

/**
 * Author: Muhammad Shahab
 * Date: 10/10/19.
 * Description: Interface that contains the services
 */

interface WebServices {

    @GET(NetworkConstants.API+ ApiEndPoints.LIVE)
    fun getCurrencyRate(@Query("access_key") accessKey : String = NetworkConstants.ACCESS_KEY.reversed(),
                               @Query("source") source : String? = null,
                               @Query(value = "currencies", encoded = true) currencies : String,
                               @Query("format") format : Int = 1): Call<Currency>
}
