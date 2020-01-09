package com.heady.test.data.modules.categories.restclient

import com.heady.test.data.modules.categories.api.CategoriesAPI
import com.heady.test.data.webservices.restclient.RestClient
import com.heady.test.data.webservices.retrofit.RetrofitProvider
import javax.inject.Inject

/**
 * RestClient For Categories Module
 *
 * Created by Praveen.
 */
class CategoriesRestClient
@Inject
constructor(private val retrofitProvider: RetrofitProvider) : RestClient {

    val categoriesAPI
        get() = getRestClient(retrofitProvider) as CategoriesAPI

    override fun getRestClient(retrofitProvider: RetrofitProvider): Any {
        return retrofitProvider.retrofit.create(CategoriesAPI::class.java)
    }
}