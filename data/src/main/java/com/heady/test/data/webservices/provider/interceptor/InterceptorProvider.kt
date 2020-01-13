package com.heady.test.data.webservices.provider.interceptor

import com.tejora.utils.Utils
import okhttp3.CacheControl
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * HTTP Interceptor Offline & Online Cache Providing
 *
 * Created by Praveen.
 */
class InterceptorProvider
@Inject
constructor(private val utils: Utils) {

    val interceptor: Interceptor
        get() = provideCacheInterceptor()

    val offlineCacheInterceptor: Interceptor
        get() = provideOfflineCacheInterceptor()

    private fun provideCacheInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())
            // re-write response header to force use of cache
            val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
        }
        utils.showLog(TAG, "provideCacheInterceptor() : $interceptor")
        return interceptor
    }

    private fun provideOfflineCacheInterceptor(): Interceptor {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            if (!utils.isInternetAvailable()) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
        utils.showLog(TAG, "provideOfflineCacheInterceptor() : $interceptor")
        return interceptor
    }

    companion object {
        private val TAG = InterceptorProvider::class.java.simpleName
        private const val CACHE_CONTROL = "Cache-Control"
    }
}