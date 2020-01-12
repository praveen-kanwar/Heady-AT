package com.heady.test.data.repository

import com.heady.test.data.database.DatabaseConfig
import com.heady.test.data.modules.categories.repository.CategoriesRepository
import com.heady.test.data.modules.products.repository.ProductsRepository
import com.heady.test.data.modules.subcategories.repository.SubCategoriesRepository
import com.heady.test.data.webservices.restclient.RestClient
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.modules.categories.beans.CategoryBean
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
    private val subCategoriesRepository: SubCategoriesRepository,
    private val productsRepository: ProductsRepository,
    private val restClient: RestClient,
    private val utils: Utils
) : Repository {


    init {
        databaseConfig.initializeRealmDatabase()
    }

    /*
     * To Fetch Data From Server
     */
    override fun fetchData(): Observable<Boolean> {
        utils.showLog(TAG, "Fetching Data From Server")
        return Observable.create { emitter ->
            try {
                // Fetching Data From Server
                val serverResponseModel = restClient.api.fetchData().blockingSingle()

                // Save Categories
                val isCategoriesSaved = categoriesRepository
                    .saveCategories(
                        serverResponseModel.categories
                    )
                    .blockingSingle()
                utils.showLog(
                    TAG,
                    "Categories Saved -> $isCategoriesSaved"
                )

                // Save Sub-Categories
                // Save Categories
                val isSubCategoriesSaved = subCategoriesRepository
                    .saveSubCategories(
                        serverResponseModel.categories
                    )
                    .blockingSingle()
                utils.showLog(
                    TAG, "SubCategories Saved -> $isSubCategoriesSaved"
                )

                // Saving Products
                val isProductsSaved =
                    productsRepository.saveProducts(serverResponseModel).blockingSingle()
                utils.showLog(TAG, "Products Saved -> $isProductsSaved")

                emitter.onNext((isProductsSaved && isSubCategoriesSaved && isCategoriesSaved))
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    override fun fetchCategories(requestBean: CategoryBeanQ): Observable<CategoryBeanR> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Saving Products")
            try {
                val categoriesCount = categoriesRepository.fetchCategoriesCount().blockingSingle()
                if (categoriesCount > 0) {
                    emitter.onNext(
                        CategoryBeanR(
                            Observable
                                .fromIterable(categoriesRepository.fetchCategories().blockingSingle())
                                .map { realmCategoryModel ->
                                    CategoryBean(
                                        realmCategoryModel.id,
                                        realmCategoryModel.name,
                                        realmCategoryModel.childCategories.toList()
                                    )
                                }
                                .toList()
                                .blockingGet()
                        )
                    )
                } else {
                    if (utils.isInternetAvailable() && fetchData().blockingSingle()) {
                        emitter.onNext(
                            CategoryBeanR(
                                Observable
                                    .fromIterable(categoriesRepository.fetchCategories().blockingSingle())
                                    .map { realmCategoryModel ->
                                        CategoryBean(
                                            realmCategoryModel.id,
                                            realmCategoryModel.name,
                                            realmCategoryModel.childCategories.toList()
                                        )
                                    }
                                    .toList()
                                    .blockingGet()
                            )
                        )
                    } else {
                        emitter.onNext(CategoryBeanR(arrayListOf()))
                    }
                }
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