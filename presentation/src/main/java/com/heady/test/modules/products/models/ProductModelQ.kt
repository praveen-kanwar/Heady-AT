package com.heady.test.modules.products.models

import com.heady.test.common.models.IModel

/**
 * Product Query Model Class
 *
 * Created by Praveen.
 */
data class ProductModelQ(
    val productIdList: List<Int>
) : IModel