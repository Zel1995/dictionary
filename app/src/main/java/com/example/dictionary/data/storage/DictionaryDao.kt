package com.example.dictionary.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dictionary.data.storage.model.DataModelEntity
import io.reactivex.Observable

@Dao
interface DictionaryDao {

  /*  @Query("SELECT * FROM DataModelEntity WHERE text = :word")
    fun search(word:String): Observable<List<DataModelEntity>>

    @Insert
    fun addWords(words:List<DataModelEntity>)*/
}
