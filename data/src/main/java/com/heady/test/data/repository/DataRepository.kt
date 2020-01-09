package com.heady.test.data.repository

import com.google.gson.Gson
import com.heady.test.data.database.DatabaseConfig
import com.heady.test.data.modules.categories.repository.CategoriesRepository
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Data Repository Responsible For Fetching Data From Local Database & Web API.
 *
 * Created by Praveen.
 */
class DataRepository
@Inject
constructor(
    databaseConfig: DatabaseConfig,
    private val categoriesRepository: CategoriesRepository,
    private val gson: Gson,
    private val utils: Utils
) : Repository {


    init {
        databaseConfig.initializeRealmDatabase()
    }

    /*
     * To Fetch Data From Server
     */
    override fun fetchData(requestBean: CategoryBeanQ): Observable<CategoryBeanR> {
        utils.showLog(TAG, "Fetching Data From Server -> ${gson.toJson(requestBean)}")
        return Observable.create { emitter ->
            try {
                emitter.onNext(categoriesRepository.fetchData(requestBean).blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    companion object {
        private const val TAG = "DataRepository"
    }

}