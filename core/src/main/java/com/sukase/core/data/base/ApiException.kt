package com.sukase.core.data.base

import com.sukase.core.domain.base.DomainThrowable

data class ApiException(
    val statusCode: String,
    val statusMessage: String,
): Exception() {
    fun map(): DomainThrowable {
        return DomainThrowable("Exception", statusMessage)
    }
}