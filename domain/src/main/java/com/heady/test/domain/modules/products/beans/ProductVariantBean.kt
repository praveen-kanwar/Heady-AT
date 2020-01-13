package com.heady.test.domain.modules.products.beans

import com.heady.test.domain.common.beans.IBean

/**
 * Domain Bean Class
 *
 * Created by Praveen.
 */
data class ProductVariantBean(
    var id: Int,
    var color: String,
    var size: Int,
    var price: Double
) : IBean