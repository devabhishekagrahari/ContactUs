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
    val contacts by viewModel.contacts.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(0) }
    val context = LocalContext.current
    var id: Int by remember{ mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = 1 }) {
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
                                showDialog = 2
                                id = contact.id
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
//0->false 1-> true 2-> edit
    if (showDialog==1 ) {
        ContactDialog(
            onDismiss = { showDialog = 0 },
            viewModel = viewModel
        )
    }
    if(showDialog==2){
        ContactDialog(
            onDismiss = { showDialog = 0 },
            viewModel = viewModel,
            text = "Update" ,
            id = id
        )
    }

}
