package com.sukase.core.domain.usecase.conversation

import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ConversationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConversationInteractor @Inject constructor(private val conversationRepository: IConversationRepository) :
    ConversationUseCase {
    override suspend fun getAllConversationList(
        token: String
    ): Flow<DomainResource<List<ConversationModel?>>> {
        return conversationRepository.getAllConversationList(token)
    }
}