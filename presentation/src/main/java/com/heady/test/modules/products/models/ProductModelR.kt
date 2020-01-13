package com.heady.test.modules.products.models

import com.heady.test.common.models.IModel

/**
 * Product Response Model Class
 *
 * Created by Praveen.
 */
data class ProductModelR(
    val productList: List<ProductModel>
) : IModel