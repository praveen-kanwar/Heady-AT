package com.heady.test.domain.modules.subcategories.beans

import com.heady.test.domain.common.beans.IBean

/**
 * Domain Bean Class
 *
 * Created by Praveen.
 */
data class SubCategoryBean(
    val id: Int,
    val name: String,
    val products: List<Int>
) : IBean