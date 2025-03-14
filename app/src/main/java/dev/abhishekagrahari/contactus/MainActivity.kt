package dev.abhishekagrahari.contactus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dev.abhishekagrahari.contactus.ui.ContactListApp
import dev.abhishekagrahari.contactus.ui.theme.ContactUsTheme
import dev.abhishekagrahari.contactus.viewmodel.ContactViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.abhishekagrahari.contactus.ViewModel.ContactViewModelFactory
import dev.abhishekagrahari.contactus.view.BaseLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactUsTheme {
                BaseLayout{
                    val viewModel = ViewModelProvider(
                        this,
                        ContactViewModelFactory(applicationContext)
                    ).get(ContactViewModel::class.java)
                    ContactListApp(viewModel)
                }
            }
            }
        }
    }

