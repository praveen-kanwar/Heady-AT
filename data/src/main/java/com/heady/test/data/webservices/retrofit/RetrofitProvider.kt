package com.heady.test.data.webservices.retrofit

import com.heady.test.data.BuildConfig
import com.heady.test.data.webservices.provider.okhttpclient.OkHttpClientProvider
import com.tejora.utils.Utils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Retrofit Provider To Create RestClient For API Calls
 *
 * Created by Praveen on 01-07-2019.
 */
class RetrofitProvider
@Inject
constructor(private val okHttpClientProvider: OkHttpClientProvider, private val utils: Utils) {

    val retrofit: Retrofit get() = provideRetrofit()

    private fun provideRetrofit(): Retrofit {
        utils.showLog(TAG, "Providing Retrofit For URL --> ${BuildConfig.BASE_URL}")
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClientProvider.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        private val TAG = RetrofitProvider::class.java.simpleName
    }
}