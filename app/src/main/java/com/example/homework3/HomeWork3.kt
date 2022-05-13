package com.example.homework3

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.homework3.database.AppDatabase

class Homework3 : Application() {
    private var _database: AppDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()
        _database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app_database.db"
        )
            .allowMainThreadQueries() //this line was deleted when I try to migrate Room to Coroutines
            .build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when {
        this is Homework3 -> database
        else -> applicationContext.appDatabase
    }

