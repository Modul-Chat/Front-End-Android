package com.sukase.core.data.model.chat.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.sukase.core.data.model.register.entity.AccountEntity

data class ChatAndReceiver(
    @Embedded
    val chat: ChatEntity,
    @Relation(
        parentColumn = "receiverId",
        entityColumn = "id"
    )
    val participants: List<AccountEntity>
)