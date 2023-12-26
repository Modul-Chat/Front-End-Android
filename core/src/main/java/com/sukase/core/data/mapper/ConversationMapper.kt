package com.sukase.core.data.mapper

import com.sukase.core.data.model.conversation.entity.ConversationEntity
import com.sukase.core.domain.model.ConversationModel
import com.sukase.core.domain.model.ParticipantModel

fun conversationMapper(input: ConversationEntity): ConversationModel =
    ConversationModel(
        id = input.id,
        name = input.name,
        photo = input.photo,
        lastMessage = input.lastMessage ?: "",
        dateTime = input.dateTime ?: "",
        participantList = input.participantsId.mapIndexed { index, i ->
            ParticipantModel(
                i.toString(),
                input.participantsUsername.elementAt(index),
                input.participantsFullName[index]
            )
        }
    )