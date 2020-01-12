package com.heady.test.data.modules.products.models

import com.heady.test.domain.common.beans.IBean

data class VariantModel(
    val id: Int,
    val color: String,
    val size: Int,
    val price: Double
) : IBean