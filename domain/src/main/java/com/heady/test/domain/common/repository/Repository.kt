package com.heady.test.domain.common.repository

import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
import com.heady.test.domain.modules.products.beans.ProductsBeanQ
import com.heady.test.domain.modules.products.beans.ProductsBeanR
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanQ
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanR
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

    /*
     * To Fetch SubCategories
     */
    fun fetchSubCategories(requestBean: SubCategoryBeanQ): Observable<SubCategoryBeanR>

    /*
     * To Fetch Products
     */
    fun fetchProducts(requestBean: ProductsBeanQ): Observable<ProductsBeanR>
}