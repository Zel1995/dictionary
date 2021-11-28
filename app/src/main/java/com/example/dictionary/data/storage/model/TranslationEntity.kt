package com.example.dictionary.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class TranslationEntity(
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "note") val note: String?
)
