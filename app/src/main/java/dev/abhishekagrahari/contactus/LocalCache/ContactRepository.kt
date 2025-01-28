package dev.abhishekagrahari.contactus.LocalCache

import dev.abhishekagrahari.contactus.model.ContactItem
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: Dao) {

    val Contacts: Flow<List<ContactItem>> = dao.getAllUsers()

    suspend fun addItem(contactItem: ContactItem) = dao.insert(contactItem)
    suspend fun deleteSelectedTask(id: Int)= dao.deleteSelectedTask(id)
}