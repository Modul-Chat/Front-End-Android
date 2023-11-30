package com.sukase.core.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sukase.core.data.model.register.entity.RegisterEntity

@Dao
interface SuKaMeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun register(registerEntity: RegisterEntity)

}