package com.heady.test.domain.modules.products.interactors

import com.heady.test.domain.common.interactor.Interactor
import com.heady.test.domain.common.repository.Repository
import com.heady.test.domain.common.threads.BackgroundThread
import com.heady.test.domain.common.threads.MainThread
import com.heady.test.domain.modules.products.beans.ProductsBeanQ
import com.heady.test.domain.modules.products.beans.ProductsBeanR
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Responsible For Task Execution Of Products Module On Data Layer
 *
 * Created by Praveen.
 */
class ProductsInteractor
@Inject
constructor(
    private val repository: Repository,
    backgroundThread: BackgroundThread,
    mainThread: MainThread
) : Interactor<ProductsBeanQ, ProductsBeanR>(backgroundThread, mainThread) {

    override fun buildInteractorObservable(requestBean: ProductsBeanQ): Observable<ProductsBeanR> {
        return repository.fetchProducts(requestBean)
    }

    companion object {
        const val TAG = "ProductsInteractor"
    }
}