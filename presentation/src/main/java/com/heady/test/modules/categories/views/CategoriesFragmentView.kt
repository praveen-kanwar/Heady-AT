package com.heady.test.modules.categories.views

import com.heady.test.common.view.BaseView
import com.heady.test.modules.categories.models.CategoryModelR

/**
 * Contract For Categories Fragment For Handling Response
 *
 * Created by Praveen.
 */
interface CategoriesFragmentView : BaseView {
    fun responseReceived(categoryModelR: CategoryModelR)
}