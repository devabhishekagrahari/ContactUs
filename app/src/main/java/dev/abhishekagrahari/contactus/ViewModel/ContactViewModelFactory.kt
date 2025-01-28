package dev.abhishekagrahari.contactus.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.abhishekagrahari.contactus.LocalCache.MyApplication
import dev.abhishekagrahari.contactus.viewmodel.ContactViewModel

class ContactViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(MyApplication.provideItemRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}