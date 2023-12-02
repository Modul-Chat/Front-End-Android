package com.sukase.core.domain.usecase.auth

import com.sukase.core.domain.base.DomainResource
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun register(username: String): Flow<DomainResource<Boolean>>
    suspend fun login(username: String): Flow<DomainResource<Boolean>>
}