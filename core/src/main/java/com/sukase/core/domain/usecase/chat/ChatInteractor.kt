package com.sukase.core.domain.usecase.chat

import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatInteractor @Inject constructor(private val chatRepository: IChatRepository) :
    ChatUseCase {
    override fun getUser(): Flow<DomainResource<UserModel>> {
        return chatRepository.getUser()
    }


    override fun getChatList(
        token: String,
        conversationId: String
    ): Flow<DomainResource<List<ChatModel?>>> {
        return chatRepository.getChatList(token, conversationId)
    }

    override fun sendChat(
        token: String,
        conversationId: String,
        message: String
    ): Flow<DomainResource<Boolean>> {
        return sendChat(token, conversationId, message)
    }
}