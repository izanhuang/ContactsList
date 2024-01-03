package com.example.contactslist.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contactslist.ContactsViewModel
import com.example.contactslist.R
import com.example.contactslist.types.BottomSheetStateType

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
 * Issues
 * Landscape orientation in add contact bottom sheet, cannot see "Add" button
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: ContactsViewModel = viewModel()) {
    val contactsScreenState = viewModel.contactsScreenState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val contactsListState = rememberLazyListState()

    LaunchedEffect(contactsScreenState.value.contactsListScrollIndex) {
        contactsScreenState.value.contactsListScrollIndex?.let { it ->
            contactsListState.animateScrollToItem(it)
            viewModel.consumeScroll()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Contacts",
                    style = MaterialTheme.typography.displaySmall,
                )
            })
        },
        floatingActionButton = {
            Button(
                onClick = {
                    viewModel.toggleAddContactBottomSheet(BottomSheetStateType.ADD_CONTACT)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_contact)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            ContactList(
                contacts = contactsScreenState.value.contacts,
                listState = contactsListState,
                onCardClick = { contact ->
                    viewModel.updateContactToUpdate(contact)
                    viewModel.toggleAddContactBottomSheet(BottomSheetStateType.EDIT_CONTACT)
                }
            )
        }
        contactsScreenState.value.currentlyOpenSheetState?.let { it ->
            when (it) {
                BottomSheetStateType.ADD_CONTACT ->
                    BasicBottomSheet(
                        contact = contactsScreenState.value.newContact,
                        updateContact = { value -> viewModel.updateNewContact(value) },
                        isContactValid = contactsScreenState.value.isNewContactValid,
                        buttonText = "Add",
                        sheetTitle = "Add contact",
                        sheetState = sheetState,
                        closeBottomSheet = { viewModel.toggleAddContactBottomSheet(null) },
                        onButtonClick = { viewModel.addContact() }
                    )

                BottomSheetStateType.EDIT_CONTACT ->
                    BasicBottomSheet(
                        contact = contactsScreenState.value.contactToUpdate,
                        updateContact = { value -> viewModel.updateContactToUpdate(value) },
                        isContactValid = contactsScreenState.value.isContactToUpdateValid,
                        buttonText = "Save",
                        sheetTitle = "Edit contact",
                        sheetState = sheetState,
                        closeBottomSheet = { viewModel.toggleAddContactBottomSheet(null) },
                        onButtonClick = { viewModel.editContact() }
                    ) {
                        Button(
                            onClick = {
                                viewModel.removeContact()
                                viewModel.toggleAddContactBottomSheet(null)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                            modifier = Modifier.padding(top = 24.dp)
                        ) {
                            Text("Delete contact")
                        }
                    }
            }
        }
    }
}