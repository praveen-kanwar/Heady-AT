package com.heady.test.modules.categories.views

import com.heady.test.common.view.BaseView
import com.heady.test.modules.categories.models.CategoryModelR

interface CategoriesFragmentView : BaseView {
    fun responseReceived(categoryModelR: CategoryModelR)
}