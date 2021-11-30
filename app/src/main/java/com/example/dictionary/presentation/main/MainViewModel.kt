package com.example.dictionary.presentation.main

import androidx.lifecycle.LiveData
import com.example.dictionary.data.AppState
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.usecases.Interactor
import com.example.dictionary.presentation.base.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: Interactor<AppState<List<DataModel>>>
) : BaseViewModel<AppState<List<DataModel>>>() {

    fun subscribe(): LiveData<AppState<List<DataModel>>> {
        return liveDataForViewToObserve

    }

    override fun loadData(word: String, isOnline: Boolean) {
        compositeDisposable += interactor.getData(word, isOnline)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(true)) }
            .doOnComplete { liveDataForViewToObserve.postValue(AppState.Loading(false)) }
            .subscribe({
                liveDataForViewToObserve.postValue(it)
            }, {
                liveDataForViewToObserve.postValue(AppState.Error(it))
            })
    }
}