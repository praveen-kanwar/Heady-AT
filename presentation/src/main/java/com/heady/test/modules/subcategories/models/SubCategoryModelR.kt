package com.heady.test.modules.subcategories.models

import com.heady.test.common.models.IModel

/**
 * SubCategory Response Model Class
 *
 * Created by Praveen.
 */
data class SubCategoryModelR(
    val subCategoriesList: List<SubCategoryModel>
) : IModel