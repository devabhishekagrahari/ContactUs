package dev.abhishekagrahari.contactus.LocalCache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.abhishekagrahari.contactus.model.ContactItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    suspend fun insert(contactItem: ContactItem)

    @Query("SELECT * FROM Contact_Table")
    fun getAllUsers(): Flow<List<ContactItem>>

    @Query("DELETE  FROM Contact_Table where id=:id")
    suspend fun deleteSelectedTask(id: Int)
}