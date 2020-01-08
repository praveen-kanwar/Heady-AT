package com.heady.test.data.webservices.restclient

import com.heady.test.data.webservices.retrofit.RetrofitProvider

/**
 * Contract For RestClient Provider
 *
 * Created by Praveen on 01-07-2019.
 */
interface RestClient {
    fun getRestClient(retrofitProvider: RetrofitProvider): Any
}