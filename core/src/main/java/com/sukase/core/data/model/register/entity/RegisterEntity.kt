package com.sukase.core.data.model.register.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class RegisterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var username: String,
)
