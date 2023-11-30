package com.sukase.core.utils

sealed class Resource<out R> private constructor(){
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val error: UiText): Resource<Nothing>()
    data object Loading: Resource<Nothing>()
    data object Empty: Resource<Nothing>()
}
