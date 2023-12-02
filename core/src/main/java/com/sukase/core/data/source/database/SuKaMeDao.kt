package com.sukase.core.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sukase.core.data.model.register.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuKaMeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun register(registerEntity: RegisterEntity)

    @Query("SELECT EXISTS(SELECT * FROM account WHERE username = :username)")
    fun isUsernameExist(username: String): Flow<Boolean>
}