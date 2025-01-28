package dev.abhishekagrahari.contactus.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import dev.abhishekagrahari.contactus.model.ContactItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ContactViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<ContactItem>>(emptyList())
    val contacts: StateFlow<List<ContactItem>> = _contacts

    fun addContact(name: String, phoneNumber: String) {
        val newContact = ContactItem(id = _contacts.value.size + 1, name = name, phoneNumber = phoneNumber)
        _contacts.value += newContact
    }

    fun deleteContact(contact: ContactItem) {
        _contacts.value -= contact
    }

    fun updateContact(contact: ContactItem, name: String, phoneNumber: String) {
        _contacts.value = _contacts.value.map {
            if (it.id == contact.id) it.copy(name = name, phoneNumber = phoneNumber) else it
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
}
