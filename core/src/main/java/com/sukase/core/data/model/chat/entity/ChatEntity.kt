package com.sukase.core.data.model.chat.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class ChatEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = 0,
    val conversationId: String,
    val message: String,
    val datetime: String,
    val senderId: Int,
    val receiverId: List<Int>,
    val receiverName: List<String>
)