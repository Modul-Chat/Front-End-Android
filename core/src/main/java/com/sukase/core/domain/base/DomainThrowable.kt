package com.sukase.core.domain.base

data class DomainThrowable(
    val type: String,
    val throwMessage: String,
): Throwable()