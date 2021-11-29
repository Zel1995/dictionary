package com.example.dictionary.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.data.AppState
import com.example.dictionary.data.datasource.DataSourceLocal
import com.example.dictionary.data.datasource.DataSourceRemote
import com.example.dictionary.data.mappers.ResponseMapper
import com.example.dictionary.data.network.RetrofitImplementation
import com.example.dictionary.data.repository.RepositoryImpl
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.usecases.Interactor
import com.example.dictionary.domain.usecases.MainInteractor
import com.example.dictionary.presentation.base.BaseViewModel
import com.example.dictionary.presentation.main.contract.Presenter
import com.example.dictionary.presentation.main.contract.MvpView
import com.example.dictionary.rx.ISchedulerProvider
import com.example.dictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class MainViewModel(
    private val interactor: Interactor<AppState<List<DataModel>>> = MainInteractor(
        RepositoryImpl(DataSourceRemote(RetrofitImplementation(), ResponseMapper())),
        RepositoryImpl(DataSourceLocal())
    )
): BaseViewModel<AppState<List<DataModel>>>() {

    fun subscribe(): LiveData<AppState<List<DataModel>>> {
        return liveDataForViewToObserve

    }    override fun loadData(word: String, isOnline: Boolean) {
        compositeDisposable += interactor.getData(word,isOnline)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(true))}
            .doOnComplete{liveDataForViewToObserve.postValue(AppState.Loading(false))}
            .subscribe({
                liveDataForViewToObserve.postValue(it)
            },{
                liveDataForViewToObserve.postValue(AppState.Error(it))
            })
    }
}