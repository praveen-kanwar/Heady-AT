package com.heady.test.modules.products.views

import com.heady.test.common.view.BaseView
import com.heady.test.modules.products.models.ProductModelR

/**
 * Contract From Product Fragment For Handling Response From Data Layer
 *
 * Created by Praveen.
 */
interface ProductsFragmentView : BaseView {
    fun responseReceived(productsModelR: ProductModelR)
}