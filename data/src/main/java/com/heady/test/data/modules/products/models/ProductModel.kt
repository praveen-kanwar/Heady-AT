package com.heady.test.data.modules.products.models

import com.google.gson.annotations.SerializedName
import com.heady.test.domain.common.beans.IBean

data class ProductModel(
    val id: Int,
    val name: String,
    @SerializedName("date_added")
    val dateAdded: String,
    val variants: List<VariantsModel>,
    val tax: TaxModel
) : IBean