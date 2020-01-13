package com.heady.test.domain.modules.products.beans

import com.heady.test.domain.common.beans.IBean

/**
 * Domain Bean Class
 *
 * Created by Praveen.
 */
data class ProductsBean(
    var id: Int,
    var name: String,
    var dateAdded: String,
    var tax: ProductTaxBean,
    var variants: List<ProductVariantBean> = arrayListOf(),
    var viewCount: Int = 0,
    var orderCount: Int = 0,
    var shares: Int = 0
) : IBean
