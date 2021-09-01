package com.cl.core.managers

import android.content.Context
import androidx.room.Room
import com.cl.core.database.AppDatabase

object DataBaseManager {

    private var appDatabase: AppDatabase? = null

    fun initialize(context: Context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "trufla_db")
                .build()
        }
    }

    fun getDB(): AppDatabase {
        return appDatabase!!
    }

    fun close() {
        return appDatabase!!.close()
    }
}
