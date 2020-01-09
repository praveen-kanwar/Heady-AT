package com.heady.test.data.modules.categories.models

import com.google.gson.annotations.SerializedName
import com.heady.test.data.modules.products.models.ProductModel
import com.heady.test.domain.common.beans.IBean

data class CategoryModel(
    val id: Int,
    val name: String,
    val products: List<ProductModel>,
    @SerializedName("child_categories")
    val childCategories: List<Int>
) : IBean