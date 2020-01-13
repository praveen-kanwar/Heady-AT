package com.heady.test.modules.subcategories.presenters

import com.heady.test.common.presenter.BasePresenter
import com.heady.test.common.transformer.Transformer
import com.heady.test.domain.modules.subcategories.beans.SubCategoryBeanQ
import com.heady.test.domain.modules.subcategories.interactors.SubCategoriesInteractor
import com.heady.test.modules.subcategories.models.SubCategoryModelQ
import com.heady.test.modules.subcategories.models.SubCategoryModelR
import com.heady.test.modules.subcategories.views.SubCategoriesFragmentView
import com.tejora.utils.Utils
import javax.inject.Inject

/**
 * SubCategory Presenter Class To Fetch SubCategories From Data Layer.
 *
 * Created by Praveen.
 */
class SubCategoriesPresenter
@Inject
constructor(
    private val subCategoriesInteractor: SubCategoriesInteractor,
    private val transformer: Transformer,
    private val subCategoriesFragmentView: SubCategoriesFragmentView,
    private val utils: Utils
) : BasePresenter(subCategoriesInteractor) {

    fun fetchCategories(subCategoryModelQ: SubCategoryModelQ) {
        utils.showLog(TAG, "Fetching SubCategory From Server")
        subCategoriesInteractor.execute(
            this::onNext,
            this::onError,
            this::onComplete,
            transformer.toBean(subCategoryModelQ, SubCategoryBeanQ::class.java)
        )
    }

    override fun onNext(responseBean: Any) {
        utils.showLog(TAG, "onNext")
        subCategoriesFragmentView.responseReceived(
            transformer.toModel(
                responseBean,
                SubCategoryModelR::class.java
            )
        )
    }

    override fun onComplete() {
        utils.showLog(TAG, "onComplete")
    }

    override fun onError(e: Throwable) {
        utils.showLog(TAG, "onError ${e.message}")
        subCategoriesFragmentView.showError(e.message!!)
    }

    companion object {
        private const val TAG = "SubCategoryPresenter"
    }
}