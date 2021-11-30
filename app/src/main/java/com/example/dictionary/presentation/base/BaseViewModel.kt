package com.example.dictionary.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionary.data.AppState
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T:AppState<List<DataModel>>>(
    val liveDataForViewToObserve:MutableLiveData<T> = MutableLiveData(),
    val compositeDisposable: CompositeDisposable= CompositeDisposable(),
    val scheduler:SchedulerProvider = SchedulerProvider()
):ViewModel() {
    val viewState:LiveData<T> = liveDataForViewToObserve

    abstract fun loadData(word:String,isOnline:Boolean)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}