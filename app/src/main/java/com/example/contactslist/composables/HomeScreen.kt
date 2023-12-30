package com.example.contactslist.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactslist.ContactsViewModel
import com.example.contactslist.R

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

/**
 * To do
 * - Phone number regex/formatting, char limit
 * - Add padding bottom to "Add" contact button
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: ContactsViewModel = viewModel()) {
    val contacts by viewModel.contacts.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Contacts",
                    style = MaterialTheme.typography.displaySmall,
                )
            })
        },
        floatingActionButton = {
            Button(onClick = { showBottomSheet = true }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_contact)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            ContactList(contacts = contacts)
        }
        if (showBottomSheet) {
            AddContactBottomSheet(
                sheetState = sheetState,
                scope = scope,
                updateShowBottomSheet = { value -> showBottomSheet = value },
                addContact = { firstName, lastName, phoneNumber, gender ->
                    viewModel.addContact(
                        firstName,
                        lastName,
                        phoneNumber,
                        gender
                    )
                }
            )
        }
    }
}