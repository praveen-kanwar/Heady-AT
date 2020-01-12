package com.heady.test.data.modules.subcategories.repository

import com.heady.test.data.modules.categories.models.CategoryModel
import com.heady.test.data.modules.subcategories.dao.SubCategoriesDao
import com.heady.test.data.modules.subcategories.models.RealmSubCategoryModel
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Repository For SubCategories Module, Responsible Local Data Operations
 *
 * Created by Praveen.
 */
class SubCategoriesRepository
@Inject
constructor(
    private val subCategoriesDao: SubCategoriesDao,
    private val utils: Utils
) {

    fun saveSubCategories(categoriesList: List<CategoryModel>): Observable<Boolean> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Saving Sub-Categories")
            try {
                emitter.onNext(
                    subCategoriesDao.saveSubCategories(
                        Observable
                            .fromIterable(categoriesList)
                            .filter { subCategoryModel -> subCategoryModel.products.isNotEmpty() }
                            .map { subCategoryModel ->
                                RealmSubCategoryModel().apply {
                                    id = subCategoryModel.id
                                    name = subCategoryModel.name
                                    products.addAll(
                                        Observable
                                            .fromIterable(subCategoryModel.products)
                                            .map { product ->
                                                product.id
                                            }
                                            .toList()
                                            .blockingGet()
                                    )
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
        private const val TAG = "SubCategoriesRepository"
    }
}