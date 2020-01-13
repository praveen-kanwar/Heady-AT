package com.heady.test.modules.categories.models

import com.heady.test.common.models.IModel

/**
 * Category Response Class
 *
 * Created by Praveen.
 */
data class CategoryModelR(
    val categoriesList: List<CategoryModel>
) : IModel