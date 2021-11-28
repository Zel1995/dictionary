package com.example.dictionary.data.datasource

import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.datasource.DataSource
import io.reactivex.Observable

class DataSourceLocal : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}