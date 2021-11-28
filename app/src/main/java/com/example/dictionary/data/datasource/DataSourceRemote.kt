package com.example.dictionary.data.datasource

import com.example.dictionary.data.mappers.ResponseMapper
import com.example.dictionary.data.network.RetrofitImplementation
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.datasource.DataSource
import io.reactivex.Observable

data class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation(),
    private val responseMapper: ResponseMapper
) :
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> =
        remoteProvider.getData(word)
            .map { it.map { dataModelResponse -> responseMapper.toDataModel(dataModelResponse) } }

}