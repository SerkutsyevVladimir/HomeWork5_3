package com.example.homework3.koin

import androidx.room.Room
import com.example.homework3.database.AppDatabase
import org.koin.dsl.module

//module with entities for access to database
val databaseModule = module {
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
}