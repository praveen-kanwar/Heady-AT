package com.heady.test.domain.common.exceptions

/**
 * Wrapper around Exceptions used to manage default errors.
 *
 * Created by Praveen.
 */
@Suppress("UNUSED")
class DefaultErrorBundle(override val exception: Exception) : ErrorBundle {

    override val errorMessage: String
        get() {
            return this.exception.message!!
        }
}
