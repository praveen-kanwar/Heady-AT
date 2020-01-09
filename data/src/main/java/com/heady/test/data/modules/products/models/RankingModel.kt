package com.heady.test.data.modules.products.models

import com.heady.test.domain.common.beans.IBean

data class RankingModel(
    val ranking: String,
    val products: List<ProductRankingModel>
) : IBean