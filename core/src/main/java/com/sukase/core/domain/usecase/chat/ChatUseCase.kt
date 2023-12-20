package com.sukase.core.domain.usecase.chat

import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ChatUseCase {
    fun getUser(): Flow<DomainResource<UserModel>>

    fun getChatList(
        token: String,
        conversationId: String
    ): Flow<DomainResource<List<ChatModel?>>>

    fun sendChat(
        token: String,
        conversationId: String,
        message: String
    ): Flow<DomainResource<Boolean>>

}