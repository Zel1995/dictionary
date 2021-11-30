package com.example.dictionary.di

import com.example.dictionary.data.datasource.DataSourceLocal
import com.example.dictionary.data.datasource.DataSourceRemote
import com.example.dictionary.data.mappers.ResponseMapper
import com.example.dictionary.data.network.RetrofitImplementation
import com.example.dictionary.data.repository.RepositoryImpl
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.datasource.DataSource
import com.example.dictionary.domain.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun providesRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> {
        return RepositoryImpl(dataSourceRemote)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun providesRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> {
        return RepositoryImpl(dataSourceLocal)
    }

    @Provides
    @Named(NAME_REMOTE)
    fun providesDataSourceRemote(retrofitImplementation: RetrofitImplementation): DataSource<List<DataModel>> =
        DataSourceRemote(retrofitImplementation, ResponseMapper())

    @Provides
    @Named(NAME_LOCAL)
    fun provideDataSourceLocal():DataSource<List<DataModel>> = DataSourceLocal()

    @Provides
    fun provideRetrofitImplementation():RetrofitImplementation = RetrofitImplementation()

}