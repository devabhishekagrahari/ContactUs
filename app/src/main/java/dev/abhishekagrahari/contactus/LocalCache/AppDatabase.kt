package dev.abhishekagrahari.contactus.LocalCache

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.abhishekagrahari.contactus.model.ContactItem

@Database(entities = [ContactItem::class], version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): Dao
}