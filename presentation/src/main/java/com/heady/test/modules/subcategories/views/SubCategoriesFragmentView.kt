package com.heady.test.modules.subcategories.views

import com.heady.test.common.view.BaseView
import com.heady.test.modules.subcategories.models.SubCategoryModelR

/**
 * SubCategories Fragment Contract For Handling Response From Data Layer.
 *
 * Created by Praveen.
 */
interface SubCategoriesFragmentView : BaseView {
    fun responseReceived(subCategoryModelR: SubCategoryModelR)
}