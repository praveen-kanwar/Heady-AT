package com.heady.test.data.modules.categories.dao

import com.google.gson.Gson
import com.heady.test.data.modules.categories.models.ServerResponseModel
import com.heady.test.domain.modules.categories.beans.CategoryBean
import com.heady.test.domain.modules.categories.beans.CategoryBeanR
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Dao For Categories Module To Perform Operations On Local Database
 *
 * Created by Praveen.
 */
class CategoriesDao
@Inject
constructor(
    private val gson: Gson,
    private val utils: Utils
) {

    /*
     * Save Data To Local DB.
     */
    fun saveData(serverResponseModel: ServerResponseModel): Observable<CategoryBeanR> {
        return Observable.create { emitter ->
            try {
                utils.showLog(
                    TAG,
                    "Saving Data To Local Database -> ${gson.toJson(serverResponseModel)}"
                )
                // Preparing CaseId Array To Fetch Receipt Issuance Data From Server
                val categoryBeanList = Observable
                    .fromIterable(serverResponseModel.categories)
                    .filter { categoryModel -> categoryModel.products.isEmpty() }
                    .map { categoryModel ->
                        CategoryBean(
                            categoryModel.id,
                            categoryModel.name,
                            categoryModel.childCategories
                        )
                    }
                    .toList()
                    .blockingGet()
                emitter.onNext(CategoryBeanR(categoryBeanList))
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    companion object {
        private const val TAG = "CategoriesDao"
    }
}