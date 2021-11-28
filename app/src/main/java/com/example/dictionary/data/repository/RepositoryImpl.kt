package com.example.dictionary.data.repository

import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.datasource.DataSource
import com.example.dictionary.domain.repository.Repository
import io.reactivex.Observable

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>):Repository<List<DataModel>> {
    override fun getData(world: String): Observable<List<DataModel>> {
        return dataSource.getData(world)
    }
}