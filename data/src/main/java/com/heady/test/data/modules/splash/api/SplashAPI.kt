package com.heady.test.data.modules.splash.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interface For Retrofit API Provider Of Splash Module
 *
 * Created by Praveen.
 */
interface SplashAPI {

    @POST("getAssignedCaseIds")
    fun fetchData(@Body request: Any): Observable<Any>

}