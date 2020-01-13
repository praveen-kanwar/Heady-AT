package com.heady.test.modules.products.models

import com.heady.test.common.models.IModel

/**
 * Product Tax Model Class
 *
 * Created by Praveen.
 */
data class ProductTaxModel(
    var name: String,
    var value: Double
) : IModel