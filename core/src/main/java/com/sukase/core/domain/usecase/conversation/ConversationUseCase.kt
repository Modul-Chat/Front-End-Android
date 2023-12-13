package com.sukase.core.domain.usecase.conversation

import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ConversationModel
import kotlinx.coroutines.flow.Flow

interface ConversationUseCase {
    suspend fun getAllConversationList(token: String): Flow<DomainResource<List<ConversationModel?>>>
}