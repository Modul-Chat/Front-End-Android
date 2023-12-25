package com.sukase.core.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sukase.core.data.model.chat.entity.ChatEntity
import com.sukase.core.data.model.conversation.entity.ConversationEntity
import com.sukase.core.data.model.register.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuKaMeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun register(accountEntity: AccountEntity)

    @Query("SELECT * FROM account WHERE username = :username AND fullName = :fullName LIMIT 1")
    fun getAccount(username: String, fullName: String): Flow<AccountEntity?>

    @Query("SELECT * FROM conversation")
    fun getConversationsList(): Flow<List<ConversationEntity?>>

    @Query("SELECT * FROM conversation where id = :conversationId")
    fun getConversation(conversationId: Int): Flow<ConversationEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addConversation(conversationEntity: ConversationEntity)

    @Transaction
    @Query("SELECT * FROM chat where id = :conversationId")
    fun getChatList(conversationId: Int): Flow<List<ChatEntity?>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun sendChat(chat: ChatEntity): Long
}