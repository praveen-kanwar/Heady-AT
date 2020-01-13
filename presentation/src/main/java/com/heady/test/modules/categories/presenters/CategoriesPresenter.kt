package com.heady.test.modules.categories.presenters

import com.heady.test.common.presenter.BasePresenter
import com.heady.test.common.transformer.Transformer
import com.heady.test.domain.modules.categories.beans.CategoryBeanQ
import com.heady.test.domain.modules.categories.interactor.CategoriesInteractor
import com.heady.test.modules.categories.models.CategoryModelQ
import com.heady.test.modules.categories.models.CategoryModelR
import com.heady.test.modules.categories.views.CategoriesFragmentView
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * Categories Presenter Class To Fetch Data From Data Layer Of Categories
 *
 * Created by Praveen.
 */
class CategoriesPresenter
@Inject
constructor(
    private val categoriesInteractor: CategoriesInteractor,
    private val transformer: Transformer,
    private val categoriesFragmentView: CategoriesFragmentView,
    private val utils: Utils
) : BasePresenter(categoriesInteractor) {

    fun fetchCategories(categoryModelQ: CategoryModelQ) {
        utils.showLog(TAG, "Fetching Category")
        categoriesFragmentView.showLoading()
        categoriesInteractor.execute(
            this::onNext,
            this::onError,
            this::onComplete,
            transformer.toBean(categoryModelQ, CategoryBeanQ::class.java)
        )
    }

    override fun onNext(responseBean: Any) {
        utils.showLog(TAG, "onNext")
        categoriesFragmentView.responseReceived(
            transformer.toModel(
                responseBean,
                CategoryModelR::class.java
            )
        )
    }

    override fun onComplete() {
        utils.showLog(TAG, "onComplete")
        categoriesFragmentView.hideLoading()
    }

    override fun onError(e: Throwable) {
        utils.showLog(TAG, "onError ${e.message}")
        categoriesFragmentView.hideLoading()
        categoriesFragmentView.showError(e.message!!)
    }

    companion object {
        private const val TAG = "CategoriesPresenter"
    }
}