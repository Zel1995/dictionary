package com.example.dictionary.di

import com.example.dictionary.presentation.MainActivity
import com.example.dictionary.presentation.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class,ViewModelModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder{
        fun build():AppComponent
    }
    fun inject(activity:MainActivity)
    fun inject(mainFragment:MainFragment)


}