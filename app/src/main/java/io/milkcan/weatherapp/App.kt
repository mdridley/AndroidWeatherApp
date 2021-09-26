package io.milkcan.weatherapp

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import io.milkcan.weatherapp.util.DatabaseHelper

class App: Application() {
    companion object {
        @JvmStatic lateinit var database: DatabaseHelper
            private set

        @JvmStatic
        lateinit var gson: Gson
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, DatabaseHelper::class.java, "weather-db")
            .fallbackToDestructiveMigration() // Cache only - allow wipes
            .build()
    }
}