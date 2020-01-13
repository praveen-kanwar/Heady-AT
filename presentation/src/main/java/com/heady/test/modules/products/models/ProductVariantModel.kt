package com.heady.test.modules.products.models

import com.heady.test.common.models.IModel

/**
 * Product Variant Model Class
 *
 * Created by Praveen.
 */
data class ProductVariantModel(
    var id: Int,
    var color: String,
    var size: Int,
    var price: Double
) : IModel