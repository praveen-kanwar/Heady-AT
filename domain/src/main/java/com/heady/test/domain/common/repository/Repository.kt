package com.heady.test.domain.common.repository

import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
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
    fun fetchData(): Observable<Boolean>

    /*
     * To Fetch Categories
     */
    fun fetchCategories(requestBean: CategoryBeanQ): Observable<CategoryBeanR>
}