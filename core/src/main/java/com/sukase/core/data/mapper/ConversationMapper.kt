package com.sukase.core.data.mapper

import com.sukase.core.data.model.conversation.entity.ConversationAndParticipants
import com.sukase.core.domain.model.ConversationModel
import com.sukase.core.domain.model.ParticipantModel

fun conversationMapper(input: ConversationAndParticipants, username: String): ConversationModel =
    ConversationModel(
        input.conversation.id,
        input.conversation.name ?: input.participants.first {
            it.username != username
        }.username,
        input.conversation.lastMessage ?: "",
        input.participants.map {
            ParticipantModel(it.id.toString(), it.username)
        }
    )