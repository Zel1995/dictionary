package com.example.dictionary.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.data.storage.model.DataModelEntity
import com.example.dictionary.data.storage.model.MeaningsEntity
import com.example.dictionary.data.storage.model.TranslationEntity


@Database(entities = [DataModelEntity::class,MeaningsEntity::class,TranslationEntity::class],version = 1)
abstract class DictionaryDataBase : RoomDatabase() {
    abstract fun createDictionaryDao():DictionaryDao
}
