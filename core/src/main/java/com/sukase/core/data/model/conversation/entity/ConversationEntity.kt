package com.sukase.core.data.model.conversation.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    val id: String,
    val name: String?,
    val lastMessage: String?,
    val participantId: Int
)
