package com.heady.test.domain.modules.products.beans

import com.heady.test.domain.common.beans.IBean

/**
 * Domain Bean Class
 *
 * Created by Praveen.
 */
data class ProductsBeanQ(
    val productIdList: List<Int>
) : IBean