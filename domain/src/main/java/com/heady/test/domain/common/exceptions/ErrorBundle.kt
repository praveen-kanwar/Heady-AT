package com.heady.test.domain.common.exceptions

/**
 * Custom Exception Contract.
 *
 * Created by Praveen.
 */
interface ErrorBundle {
    val exception: Exception

    val errorMessage: String
}
