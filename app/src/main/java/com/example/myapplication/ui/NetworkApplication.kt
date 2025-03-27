package com.example.myapplication.ui

import android.app.Application
import com.example.myapplication.ui.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class NetworkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@NetworkApplication)
            modules(appModules)
        }
    }
}