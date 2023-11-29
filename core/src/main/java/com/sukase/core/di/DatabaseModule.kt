package com.sukase.core.di

import android.content.Context
import androidx.room.Room
import com.sukase.core.data.source.database.SuKaMeDao
import com.sukase.core.data.source.database.SuKaMeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SuKaMeDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("example".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            SuKaMeDatabase::class.java,
            "SuKame.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideAnimeDao(database: SuKaMeDatabase): SuKaMeDao = database.suKaMeDao()
}