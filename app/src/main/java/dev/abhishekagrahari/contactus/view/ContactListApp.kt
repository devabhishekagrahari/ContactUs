package dev.abhishekagrahari.contactus.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.abhishekagrahari.contactus.model.ContactItem
import dev.abhishekagrahari.contactus.view.ContactDialog
import dev.abhishekagrahari.contactus.view.ContactListItem

import dev.abhishekagrahari.contactus.viewmodel.ContactViewModel
import android.content.Context
import androidx.compose.ui.platform.LocalContext


@Composable
fun ContactListApp(viewModel: ContactViewModel) {
    val contacts by viewModel.contacts.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf<ContactItem?>(null) }
 val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (contacts.isEmpty()) {
                Text(
                    text = "No contacts found. Add a new one!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(contacts) { contact ->
                        ContactListItem(
                            contact = contact,
                            onEditClick = {
                                selectedContact = contact
                                showDialog = true
                            },
                            onDeleteClick = { viewModel.deleteContact(contact) },
                            onCallClick = {
                                val phoneNumber = contact.phoneNumber

                                if (phoneNumber.isNotBlank()) {
                                    viewModel.openDialer(context, phoneNumber)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Phone number is empty",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        ContactDialog(
            contact = selectedContact,
            onDismiss = { showDialog = false },
            onSave = { name, phoneNumber ->
                if (selectedContact == null) {
                    viewModel.addContact(name, phoneNumber)
                } else {
                    viewModel.updateContact(selectedContact!!, name, phoneNumber)
                }
                selectedContact = null
                showDialog = false
            }
        )
    }
}
