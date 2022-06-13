package com.example.homework3

import android.app.Application
import com.example.homework3.data.koin.dataModule
import com.example.homework3.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Homework3 : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Homework3)
            modules(
                dataModule,
                viewModelModule
            )
        }
    }
}
