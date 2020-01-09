package com.heady.test.data.modules.products.models

import com.google.gson.annotations.SerializedName
import com.heady.test.domain.common.beans.IBean

data class ProductRankingModel(
    val id: Int,
    @SerializedName("view_count")
    val viewCount: Int = -1,
    @SerializedName("order_count")
    val orderCount: Int = -1,
    val shares: Int = -1
) : IBean