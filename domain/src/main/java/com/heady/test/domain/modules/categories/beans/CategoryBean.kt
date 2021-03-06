package com.heady.test.domain.modules.categories.beans

import com.heady.test.domain.common.beans.IBean

/**
 * Domain Bean Class
 *
 * Created by Praveen.
 */
data class CategoryBean(
    val id: Int,
    val name: String,
    val childCategories: List<Int>
) : IBean