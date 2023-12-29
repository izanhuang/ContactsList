package com.example.contactslist.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslist.ContactsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/*
* List of contacts
* - Includes first and last name, phone number, and an avatar
* - Ordered alphabetically
* - Has "Add contact" button which opens a bottom sheet
*/

/* 
* Bottom sheet
* - Allow creating of new contact
* - Once done and created/saved, close bottom sheet, and scroll to it in list
*/

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: ContactsViewModel = viewModel()) {
    val contacts by viewModel.contacts.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Text(
                text = "Contacts",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(8.dp)
            )
            ContactList(contacts = contacts)
        }
    }
}