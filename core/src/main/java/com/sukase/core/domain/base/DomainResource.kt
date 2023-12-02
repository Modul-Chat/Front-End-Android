package com.sukase.core.domain.base

import com.sukase.core.utils.UiText

sealed class DomainResource<out R> private constructor(){
    data class Success<out T>(val data: T): DomainResource<T>()
    data class Error(val error: UiText): DomainResource<Nothing>()
    data object Loading: DomainResource<Nothing>()
    data object Empty: DomainResource<Nothing>()
}
