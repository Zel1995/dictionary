package com.example.dictionary.domain.usecases

import com.example.dictionary.data.AppState
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState<List<DataModel>>> {
    override fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): Observable<AppState<List<DataModel>>> {
        return if (fromRemoteSource) {
            remoteRepository
                .getData(word)
                .map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}