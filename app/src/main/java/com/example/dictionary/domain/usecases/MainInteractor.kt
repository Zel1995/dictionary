package com.example.dictionary.domain.usecases

import com.example.dictionary.data.AppState
import com.example.dictionary.di.NAME_LOCAL
import com.example.dictionary.di.NAME_REMOTE
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE)private val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL)private val localRepository: Repository<List<DataModel>>
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