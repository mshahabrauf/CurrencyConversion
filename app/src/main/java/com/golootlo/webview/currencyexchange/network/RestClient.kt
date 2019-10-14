package com.golootlo.webview.currencyexchange.network

import android.app.Activity
import com.golootlo.webview.currencyexchange.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Muhammad Shahab on 7/20/16.
 */
object RestClient {

    private val TIMEOUT = 25
    private var logging: HttpLoggingInterceptor? = null
    private val TAG: String = "RESTCLIENT"


    init {
        setupClient()
    }

    private fun setupClient() {
        setupLogging()
    }

    private fun setupLogging() {
        logging = HttpLoggingInterceptor()
        logging!!.level = if ( BuildConfig.DEBUG ) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun getRestAdapter(): WebServices {
        val httpClient : OkHttpClient
            httpClient = OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS).writeTimeout(
                    TIMEOUT.toLong(),
                            TimeUnit.SECONDS).readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder().
                baseUrl(NetworkConstants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(httpClient).
                build()

        return retrofit.create<WebServices>(WebServices::class.java)
    }





}
