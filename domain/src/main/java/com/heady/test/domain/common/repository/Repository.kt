package com.heady.test.domain.common.repository

import io.reactivex.Observable


/**
 * Contract For Data Repository
 *
 * Created by Praveen.
 */
interface Repository {

    /*
     * To Fetch Data
     */
    fun fetchData(): Observable<*>
}