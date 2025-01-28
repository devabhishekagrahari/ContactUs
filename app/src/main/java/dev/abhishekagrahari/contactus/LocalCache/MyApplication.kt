package dev.abhishekagrahari.contactus.LocalCache

import android.content.Context
import androidx.room.Room

object MyApplication {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
    fun provideItemRepository(context: Context): ContactRepository {
        val dao = getDatabase(context).userDao()
        return ContactRepository(dao)
    }
}