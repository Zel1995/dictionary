package com.example.dictionary.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.R
import com.example.dictionary.di.App
import com.example.dictionary.di.AppComponent
import com.example.dictionary.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {
    var appComponent: AppComponent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
        appComponent = (application as App).appComponent
        appComponent?.inject(this)
    }
}