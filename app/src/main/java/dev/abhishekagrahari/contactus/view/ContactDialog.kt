package dev.abhishekagrahari.contactus.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.abhishekagrahari.contactus.model.ContactItem
import dev.abhishekagrahari.contactus.viewmodel.ContactViewModel

@Composable
fun ContactDialog(
    onDismiss: () -> Unit,
    viewModel: ContactViewModel,
    text: String= "Save" ,
    id: Int =0
) {
    var name by remember { mutableStateOf( "") }
    var phoneNumber by remember { mutableStateOf( "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {  Text("Add Contact" ) },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                    if ( text=="Save"){
                    // pass a function to add the elements
                    viewModel.addContact(name, phoneNumber)
                    }
                    else{
                        viewModel.updateContact(ContactItem(name = name, phoneNumber= phoneNumber , id = id))
                    }
                    name = "" // Clear the fields after submit
                    phoneNumber = ""
                }
            }) {
                Text(text = text)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    )
}
