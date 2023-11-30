package com.sukase.core.domain.usecase.auth

import com.sukase.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun register(username: String): Flow<Resource<Boolean>>
    suspend fun login(username: String): Flow<Resource<Boolean>>
}