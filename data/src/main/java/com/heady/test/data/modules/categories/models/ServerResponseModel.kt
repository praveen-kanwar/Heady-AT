package com.heady.test.data.modules.categories.models

import com.heady.test.data.modules.products.models.RankingModel
import com.heady.test.domain.common.beans.IBean

data class ServerResponseModel(
    val categories: List<CategoryModel>,
    val rankings: List<RankingModel>
) : IBean