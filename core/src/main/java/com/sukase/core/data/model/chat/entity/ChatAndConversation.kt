package com.sukase.core.data.model.chat.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.sukase.core.data.model.conversation.entity.ConversationEntity

data class ChatAndConversation(
    @Embedded
    val chat: ChatEntity,
    @Relation(
        parentColumn = "conversationId",
        entityColumn = "id"
    )
    val conversation: ConversationEntity
)