package com.example.dictionary.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.R
import com.example.dictionary.data.AppState
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.presentation.main.MainFragment
import com.example.dictionary.presentation.main.contract.Presenter
import com.example.dictionary.presentation.main.contract.MvpView

class MainActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container,MainFragment()).commit()
    }
}