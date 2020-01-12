package com.heady.test.data.modules.categories.repository

import com.heady.test.data.modules.categories.dao.CategoriesDao
import com.heady.test.data.modules.categories.models.CategoryModel
import com.heady.test.data.modules.categories.models.RealmCategoryModel
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Repository For Categories Module, Responsible For Local Data Operations
 *
 * Created by Praveen.
 */
class CategoriesRepository
@Inject
constructor(
    private val categoriesDao: CategoriesDao,
    private val utils: Utils
) {

    fun fetchCategoriesCount(): Observable<Int> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Fetching Categories Count")
            try {
                emitter.onNext(categoriesDao.fetchCategoriesCount().blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    fun fetchCategories(): Observable<List<RealmCategoryModel>> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Fetching Categories List")
            try {
                emitter.onNext(categoriesDao.fetchCategories().blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    fun saveCategories(categoriesList: List<CategoryModel>): Observable<Boolean> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Saving Categories")
            try {
                emitter.onNext(
                    categoriesDao.saveCategories(
                        Observable
                            .fromIterable(categoriesList)
                            .filter { categoryModel -> categoryModel.products.isEmpty() }
                            .map { categoryModel ->
                                RealmCategoryModel().apply {
                                    id = categoryModel.id
                                    name = categoryModel.name
                                    childCategories.addAll(categoryModel.childCategories)
                                }
                            }
                            .toList()
                            .blockingGet()
                    ).blockingSingle()
                )
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