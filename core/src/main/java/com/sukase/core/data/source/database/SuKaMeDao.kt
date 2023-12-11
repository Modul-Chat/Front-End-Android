package com.sukase.core.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sukase.core.data.model.chat.entity.ChatAndReceiver
import com.sukase.core.data.model.conversation.entity.ConversationAndParticipants
import com.sukase.core.data.model.register.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuKaMeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun register(accountEntity: AccountEntity)

    @Query("SELECT EXISTS(SELECT * FROM account WHERE username = :username)")
    fun isUsernameExist(username: String): Flow<Boolean>

    @Transaction
    @Query("SELECT * FROM conversation")
    fun getConversationsList(): Flow<List<ConversationAndParticipants>>

    @Transaction
    @Query("SELECT * FROM chat")
    fun getChatList(): Flow<List<ChatAndReceiver>>
}