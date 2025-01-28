package dev.abhishekagrahari.contactus.LocalCache

import dev.abhishekagrahari.contactus.model.ContactItem
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: Dao) {

    val Contacts: Flow<List<ContactItem>> = dao.getAllUsers()

    suspend fun addItem(todo: ContactItem) = dao.insert(todo)
    suspend fun deleteSelectedTask(id: Int)= dao.deleteSelectedTask(id)
}