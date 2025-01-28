package dev.abhishekagrahari.contactus.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.abhishekagrahari.contactus.LocalCache.ContactRepository
import dev.abhishekagrahari.contactus.model.ContactItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ContactViewModel(private val repository: ContactRepository) : ViewModel() {
    val contacts : Flow<List<ContactItem>> = repository.Contacts
    fun addContact(name: String, phoneNumber: String) {
            viewModelScope.launch {
            repository.addItem(ContactItem(name = name , phoneNumber = phoneNumber ))
        }
    }

    fun deleteContact(contact: ContactItem) {
        viewModelScope.launch {
            repository.deleteSelectedTask(contact.id)
        }
    }

    fun openDialer(context: Context, phoneNumber: String) {
        try {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            context.startActivity(dialIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Failed to open dialer", Toast.LENGTH_SHORT).show()
        }
    }
    fun updateContact(contact: ContactItem) {
        viewModelScope.launch{
            repository.updateContact(contact)
        }
    }
}
