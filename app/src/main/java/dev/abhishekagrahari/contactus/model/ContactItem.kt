package dev.abhishekagrahari.contactus.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact_Table")
data class ContactItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int =1 ,

    var name: String,
    var phoneNumber: String,
    var isEditing: Boolean = false
)