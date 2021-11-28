package com.example.dictionary.data.storage

import android.app.Application
import androidx.room.Room

class DbApp : Application() {

  /*  companion object {
        private const val DB_NAME = "DictionaryDataBase"
        private var dao: DictionaryDao? = null
        fun getDao() = dao ?: RuntimeException("db has not been created")

    }

    override fun onCreate() {
        super.onCreate()
        dao = Room.databaseBuilder(this, DictionaryDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build().createDictionaryDao()

    }*/
}
