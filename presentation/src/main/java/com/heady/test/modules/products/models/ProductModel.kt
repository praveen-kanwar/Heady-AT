package com.heady.test.modules.products.models

import com.heady.test.common.models.IModel

/**
 * Product Model Class
 *
 * Created by Praveen.
 */
data class ProductModel(
    var id: Int,
    var name: String,
    var dateAdded: String,
    var tax: ProductTaxModel,
    var variants: List<ProductVariantModel> = arrayListOf(),
    var viewCount: Int = 0,
    var orderCount: Int = 0,
    var shares: Int = 0
) : IModel