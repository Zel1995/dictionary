package com.example.dictionary.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataModelEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "text") val text: String?,
    @Embedded
    @ColumnInfo(name = "meanings") val meanings: List<MeaningsEntity>?
)
