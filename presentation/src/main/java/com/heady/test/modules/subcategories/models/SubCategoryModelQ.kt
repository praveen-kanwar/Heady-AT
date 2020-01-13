package com.heady.test.modules.subcategories.models

import com.heady.test.common.models.IModel

/**
 * SubCategory Query Model Class
 *
 * Created by Praveen.
 */
data class SubCategoryModelQ(
    val childCategories: List<Int>
) : IModel