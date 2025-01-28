package dev.abhishekagrahari.contactus.model

data class ContactItem(
    val id: Int,
    var name: String,
    var phoneNumber: String,
    var isEditing: Boolean = false
)