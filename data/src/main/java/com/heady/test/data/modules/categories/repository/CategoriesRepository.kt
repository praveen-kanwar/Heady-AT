package com.heady.test.data.modules.categories.repository

import com.google.gson.Gson
import com.heady.test.data.modules.categories.dao.CategoriesDao
import com.heady.test.data.modules.categories.restclient.CategoriesRestClient
import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Repository For Categories Module, Responsible For Web & Local Data Operations
 *
 * Created by Praveen.
 */
class CategoriesRepository
@Inject
constructor(
    private val categoriesDao: CategoriesDao,
    private val categoriesRestClient: CategoriesRestClient,
    private val gson: Gson,
    private val utils: Utils
) {

    fun fetchData(requestBean: CategoryBeanQ): Observable<CategoryBeanR> {
        return Observable.create { emitter ->
            try {
                utils.showLog(TAG, "Fetching Data From Server -> ${gson.toJson(requestBean)}")
                emitter.onNext(categoriesDao.saveData(categoriesRestClient.categoriesAPI.fetchData().blockingSingle()).blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    companion object {
        private const val TAG = "CategoriesRepository"
    }
}