package com.heady.test.data.webservices.api

import com.heady.test.data.modules.categories.models.ServerResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Interface For Retrofit API Provider Of Categories Module
 *
 * Created by Praveen.
 */
interface API {

    @GET("json")
    fun fetchData(): Observable<ServerResponseModel>

}