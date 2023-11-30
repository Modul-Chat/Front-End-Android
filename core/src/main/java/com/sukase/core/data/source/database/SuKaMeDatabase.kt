package com.sukase.core.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sukase.core.data.model.register.entity.RegisterEntity

@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class SuKaMeDatabase : RoomDatabase() {
    abstract fun suKaMeDao(): SuKaMeDao
}