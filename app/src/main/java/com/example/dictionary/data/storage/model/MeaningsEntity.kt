package com.example.dictionary.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
@Entity
data class MeaningsEntity(
    @ColumnInfo(name = "id") val id: Int,
    @Embedded
    @ColumnInfo(name = "translation") val translation: TranslationEntity?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "previewUrl") val previewUrl: String?
)
