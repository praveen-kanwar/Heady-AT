package com.heady.test.modules.subcategories.models

import com.heady.test.common.models.IModel

/**
 * SubCategory Model Class
 *
 * Created by Praveen.
 */
data class SubCategoryModel(
    val id: Int,
    val name: String,
    val products: List<Int>
) : IModel