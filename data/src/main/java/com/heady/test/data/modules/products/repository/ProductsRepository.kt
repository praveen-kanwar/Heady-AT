package com.heady.test.data.modules.products.repository

import com.heady.test.data.modules.categories.models.ServerResponseModel
import com.heady.test.data.modules.products.dao.ProductsDao
import com.heady.test.data.modules.products.models.ProductRankingModel
import com.heady.test.data.modules.products.models.RealmProductModel
import com.heady.test.data.modules.products.models.RealmTaxModel
import com.heady.test.data.modules.products.models.RealmVariantModel
import com.heady.test.domain.modules.products.beans.ProductsBean
import com.tejora.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Repository For Products Module, Responsible Local Data Operations
 *
 * Created by Praveen.
 */
class ProductsRepository
@Inject
constructor(
    private val productsDao: ProductsDao,
    private val utils: Utils
) {

    private val rankingOrderedList = arrayListOf<ProductRankingModel>()
    private val rankingSharedList = arrayListOf<ProductRankingModel>()
    private val rankingViewList = arrayListOf<ProductRankingModel>()

    fun saveProducts(serverResponseModel: ServerResponseModel): Observable<Boolean> {
        return Observable.create { emitter ->
            // Populate Product OrderedList
            rankingOrderedList.clear()
            rankingOrderedList.addAll(
                Observable.fromIterable(serverResponseModel.rankings)
                    .filter { rankingModel -> rankingModel.ranking == MOST_ORDERED_PRODUCTS }
                    .blockingSingle()
                    .products
            )
            // Populate Product SharedList
            rankingSharedList.clear()
            rankingSharedList.addAll(
                Observable.fromIterable(serverResponseModel.rankings)
                    .filter { rankingModel -> rankingModel.ranking == MOST_SHARED_PRODUCTS }
                    .blockingSingle()
                    .products
            )
            // Populate Product ViewList
            rankingViewList.clear()
            rankingViewList.addAll(
                Observable.fromIterable(serverResponseModel.rankings)
                    .filter { rankingModel -> rankingModel.ranking == MOST_VIEWED_PRODUCTS }
                    .blockingSingle()
                    .products
            )
            utils.showLog(TAG, "Saving Products")
            try {
                emitter.onNext(productsDao.saveProducts(getProductList(serverResponseModel).blockingSingle()).blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    private fun getProductList(serverResponseModel: ServerResponseModel): Observable<List<RealmProductModel>> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Generating Product List")
            try {
                emitter.onNext(
                    Observable
                        .fromIterable(serverResponseModel.categories)
                        .filter { categoryModel ->
                            categoryModel.products.isNotEmpty()
                        }
                        .flatMapIterable { categoryModel ->
                            categoryModel.products
                        }
                        .map { productModel ->
                            RealmProductModel().apply {
                                id = productModel.id
                                name = productModel.name
                                dateAdded = productModel.dateAdded
                                variants.addAll(
                                    Observable
                                        .fromIterable(productModel.variants)
                                        .map { variant ->
                                            RealmVariantModel().apply {
                                                id = variant.id
                                                color = variant.color
                                                size = variant.size
                                                price = variant.price
                                            }
                                        }
                                        .toList()
                                        .blockingGet()
                                )
                                tax = RealmTaxModel().apply {
                                    name = productModel.tax.name
                                    value = productModel.tax.value
                                }

                                try {
                                    viewCount = Observable
                                        .fromIterable(rankingViewList)
                                        .filter { productRankingModel -> (productRankingModel.id == productModel.id) }
                                        .blockingFirst()
                                        .viewCount
                                } catch (exception: Exception) {
                                    viewCount = 0
                                    utils.showLog(
                                        TAG,
                                        "Error ${exception.message} While Fetching View Count For Product -> ${productModel.name}"
                                    )
                                }

                                try {
                                    orderCount = Observable
                                        .fromIterable(rankingOrderedList)
                                        .filter { productRankingModel -> (productRankingModel.id == productModel.id) }
                                        .blockingFirst()
                                        .orderCount
                                } catch (exception: Exception) {
                                    orderCount = 0
                                    utils.showLog(
                                        TAG,
                                        "Error ${exception.message} While Fetching Order Count For Product -> ${productModel.name}"
                                    )
                                }

                                try {
                                    shares = Observable
                                        .fromIterable(rankingSharedList)
                                        .filter { productRankingModel -> (productRankingModel.id == productModel.id) }
                                        .blockingFirst()
                                        .shares
                                } catch (exception: Exception) {
                                    shares = 0
                                    utils.showLog(
                                        TAG,
                                        "Error ${exception.message} While Fetching Shares Count For Product -> ${productModel.name}"
                                    )
                                }
                            }
                        }
                        .toList()
                        .blockingGet()
                )
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    fun fetchProducts(productsIdArray: Array<Int>): Observable<List<ProductsBean>> {
        return Observable.create { emitter ->
            utils.showLog(TAG, "Fetching Sub-Categories")
            try {
                emitter.onNext(productsDao.fetchProducts(productsIdArray).blockingSingle())
                emitter.onComplete()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }
    }

    companion object {
        private const val TAG = "ProductsRepository"
        private const val MOST_ORDERED_PRODUCTS = "Most OrdeRed Products"
        private const val MOST_SHARED_PRODUCTS = "Most ShaRed Products"
        private const val MOST_VIEWED_PRODUCTS = "Most Viewed Products"
    }
}