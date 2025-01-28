package dev.abhishekagrahari.contactus.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact_Table")
data class ContactItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0 ,
    var name: String="",
    var phoneNumber: String=""
)