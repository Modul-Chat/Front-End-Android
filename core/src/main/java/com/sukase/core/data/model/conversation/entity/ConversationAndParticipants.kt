package com.sukase.core.data.model.conversation.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.sukase.core.data.model.register.entity.AccountEntity

data class ConversationAndParticipants(
    @Embedded
    val conversation: ConversationEntity,
    @Relation(
        parentColumn = "participantId",
        entityColumn = "id"
    )
    val participants: List<AccountEntity>
)