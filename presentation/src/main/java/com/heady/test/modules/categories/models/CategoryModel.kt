package com.heady.test.modules.categories.models

import com.heady.test.common.models.IModel

data class CategoryModel(
    val id: Int,
    val name: String,
    val childCategories: List<Int>
) : IModel