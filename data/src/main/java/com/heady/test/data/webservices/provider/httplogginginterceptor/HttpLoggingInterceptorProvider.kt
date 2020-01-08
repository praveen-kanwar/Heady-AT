package com.heady.test.data.webservices.provider.httplogginginterceptor

import com.tejora.utils.Utils
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * HTTP Interceptor For Logging API Calls
 *
 * Created by Praveen on 01-07-2019.
 */
class HttpLoggingInterceptorProvider
@Inject
constructor(private val utils: Utils) {

    val instance: HttpLoggingInterceptor get() = provideHttpLoggingInterceptor()

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        utils.showLog(TAG, "provideHttpLoggingInterceptor() : $httpLoggingInterceptor")
        return httpLoggingInterceptor
    }

    companion object {
        private val TAG = HttpLoggingInterceptorProvider::class.java.simpleName
    }
}