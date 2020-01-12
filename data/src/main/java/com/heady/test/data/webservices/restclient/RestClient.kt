package com.heady.test.data.webservices.restclient

import com.heady.test.data.webservices.api.API
import com.heady.test.data.webservices.retrofit.RetrofitProvider
import javax.inject.Inject

/**
 * RestClient For API Consumption
 *
 * Created by Praveen.
 */
class RestClient
@Inject
constructor(private val retrofitProvider: RetrofitProvider) {

    val api
        get() = getRestClient(retrofitProvider) as API

    private fun getRestClient(retrofitProvider: RetrofitProvider): Any {
        return retrofitProvider.retrofit.create(API::class.java)
    }
}