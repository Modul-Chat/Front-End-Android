package com.sukase.core.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false)
abstract class SuKaMeDatabase : RoomDatabase() {
    abstract fun suKaMeDao(): SuKaMeDao
}