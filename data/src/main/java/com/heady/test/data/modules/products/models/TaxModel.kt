package com.heady.test.data.modules.products.models

import com.heady.test.domain.common.beans.IBean

data class TaxModel(
    val name: String,
    val value: Double
) : IBean