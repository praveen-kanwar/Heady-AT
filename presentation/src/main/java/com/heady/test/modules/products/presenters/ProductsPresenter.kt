package com.heady.test.modules.products.presenters

import com.heady.test.common.presenter.BasePresenter
import com.heady.test.common.transformer.Transformer
import com.heady.test.domain.modules.products.beans.ProductsBeanQ
import com.heady.test.domain.modules.products.interactors.ProductsInteractor
import com.heady.test.modules.products.models.ProductModelQ
import com.heady.test.modules.products.models.ProductModelR
import com.heady.test.modules.products.views.ProductsFragmentView
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * Products Presenter Class Responsible For Fetching Products From Data Layer
 *
 * Created by Praveen.
 */
class ProductsPresenter
@Inject
constructor(
    private val productsInteractor: ProductsInteractor,
    private val transformer: Transformer,
    private val productsFragmentView: ProductsFragmentView,
    private val utils: Utils
) : BasePresenter(productsInteractor) {

    fun fetchCategories(productModelQ: ProductModelQ) {
        utils.showLog(TAG, "Fetching Products From Data Layer")
        productsInteractor.execute(
            this::onNext,
            this::onError,
            this::onComplete,
            transformer.toBean(productModelQ, ProductsBeanQ::class.java)
        )
    }

    override fun onNext(responseBean: Any) {
        utils.showLog(TAG, "onNext")
        productsFragmentView.responseReceived(
            transformer.toModel(
                responseBean,
                ProductModelR::class.java
            )
        )
    }

    override fun onComplete() {
        utils.showLog(TAG, "onComplete")
    }

    override fun onError(e: Throwable) {
        utils.showLog(TAG, "onError ${e.message}")
        productsFragmentView.showError(e.message!!)
    }

    companion object {
        private const val TAG = "ProductsPresenter"
    }
}