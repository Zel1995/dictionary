package com.example.dictionary.presentation.main.contract

import com.example.dictionary.data.AppState
import com.example.dictionary.domain.Model.DataModel

interface MvpView {
    fun renderData(appState: AppState<List<DataModel>>)
}