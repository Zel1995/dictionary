package com.example.dictionary.presentation.main

import com.example.dictionary.data.AppState
import com.example.dictionary.data.datasource.DataSourceLocal
import com.example.dictionary.data.datasource.DataSourceRemote
import com.example.dictionary.data.mappers.ResponseMapper
import com.example.dictionary.data.network.RetrofitImplementation
import com.example.dictionary.data.repository.RepositoryImpl
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.usecases.Interactor
import com.example.dictionary.domain.usecases.MainInteractor
import com.example.dictionary.presentation.main.contract.Presenter
import com.example.dictionary.presentation.main.contract.MvpView
import com.example.dictionary.rx.ISchedulerProvider
import com.example.dictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class MainPresenterImpl(
    private val interactor: Interactor<AppState<List<DataModel>>> = MainInteractor(
        RepositoryImpl(DataSourceRemote(RetrofitImplementation(), ResponseMapper())),
        RepositoryImpl(DataSourceLocal())
    ),
    private val schedulerProvider: ISchedulerProvider = SchedulerProvider()
) : Presenter<MvpView> {
    private val compositeDisposable = CompositeDisposable()

    private var currentView: MvpView? = null
    override fun attachView(view: MvpView) {
        currentView = view
    }

    override fun detachView(view: MvpView) {
        if(view == currentView) {
            compositeDisposable.clear()
            compositeDisposable.dispose()
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable += interactor.getData(word,isOnline)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe{ currentView?.renderData(AppState.Loading(true)) }
            .doOnComplete{currentView?.renderData(AppState.Loading(false))}
            .subscribe({
                       currentView?.renderData(it)
            },{
                currentView?.renderData(AppState.Error(it))
            })
    }
}