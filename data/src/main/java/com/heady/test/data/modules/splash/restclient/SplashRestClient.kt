package com.heady.test.data.modules.splash.restclient

import com.heady.test.data.modules.splash.api.SplashAPI
import com.heady.test.data.webservices.restclient.RestClient
import com.heady.test.data.webservices.retrofit.RetrofitProvider
import javax.inject.Inject

/**
 * RestClient For Splash Module
 *
 * Created by Praveen on 01-07-2019.
 */
class SplashRestClient
@Inject
constructor(private val retrofitProvider: RetrofitProvider) : RestClient {

    val splashAPI
        get() = getRestClient(retrofitProvider) as SplashAPI

    override fun getRestClient(retrofitProvider: RetrofitProvider): Any {
        return retrofitProvider.retrofit.create(SplashAPI::class.java)
    }
}