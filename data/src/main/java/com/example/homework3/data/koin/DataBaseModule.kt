package com.example.homework3.data.koin

import androidx.room.Room
import com.example.homework3.data.database.AppDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().githubDao() }

    single { get<AppDatabase>().githubCashedDao() }
}