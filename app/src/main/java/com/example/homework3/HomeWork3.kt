package com.example.homework3

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.homework3.database.AppDatabase
import com.example.homework3.koin.databaseModule
import com.example.homework3.koin.networkModule
import com.example.homework3.koin.repositoryModule
import com.example.homework3.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Homework3 : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Homework3)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
